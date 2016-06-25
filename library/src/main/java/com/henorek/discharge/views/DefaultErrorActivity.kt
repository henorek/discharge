/*
 * Copyright 2015 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.henorek.discharge.views

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.henorek.discharge.Fuse
import com.henorek.discharge.R

class DefaultErrorActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.fuse_default_error_activity)

    val restartButton = findViewById(R.id.fuse_error_activity_restart_button) as Button

    val restartActivityClass = Fuse.getRestartActivityClassFromIntent(intent)

    if (restartActivityClass != null) {
      restartButton.setText(R.string.fuse_error_activity_restart_app)
      restartButton.setOnClickListener {
        val intent = Intent(this@DefaultErrorActivity, restartActivityClass)
        Fuse.restartApplicationWithIntent(this@DefaultErrorActivity, intent)
      }
    } else {
      restartButton.setOnClickListener { Fuse.closeApplication(this@DefaultErrorActivity) }
    }

    val moreInfoButton = findViewById(R.id.fuse_error_activity_more_info_button) as Button

    if (Fuse.isShowErrorDetailsFromIntent(intent)) {

      moreInfoButton.setOnClickListener {
        //We retrieve all the error data and show it

        val dialog = AlertDialog.Builder(this@DefaultErrorActivity)
            .setTitle(R.string.fuse_error_activity_error_details_title)
            .setMessage(Fuse.getAllErrorDetailsFromIntent(this@DefaultErrorActivity, intent))
            .setPositiveButton(R.string.fuse_error_activity_error_details_close, null)
            .setNeutralButton(R.string.fuse_error_activity_error_details_copy) { dialog, which ->
              copyErrorToClipboard()
              Toast.makeText(this@DefaultErrorActivity, R.string.fuse_error_activity_error_details_copied,
                  Toast.LENGTH_SHORT).show()
            }.show()
        val textView = dialog.findViewById(android.R.id.message) as TextView
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.fuse_error_activity_error_details_text_size))
      }
    } else {
      moreInfoButton.visibility = View.GONE
    }

    val defaultErrorActivityDrawableId = Fuse.getDefaultErrorActivityDrawableIdFromIntent(intent)
    val errorImageView = findViewById(R.id.fuse_error_activity_image) as ImageView
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      errorImageView.setImageDrawable(resources.getDrawable(defaultErrorActivityDrawableId, theme))
    } else {
      //noinspection deprecation
      errorImageView.setImageDrawable(resources.getDrawable(defaultErrorActivityDrawableId))
    }
  }

  private fun copyErrorToClipboard() {
    val errorInformation = Fuse.getAllErrorDetailsFromIntent(this@DefaultErrorActivity, intent)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
      val clip = ClipData.newPlainText(getString(R.string.fuse_error_activity_error_details_clipboard_label),
          errorInformation)
      clipboard.primaryClip = clip
    } else {
      //noinspection deprecation
      val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
      clipboard.text = errorInformation
    }
  }
}
