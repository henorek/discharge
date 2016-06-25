package com.henorek.discharge.solutions.builders

import android.content.Context
import android.widget.Toast
import com.henorek.discharge.Solvable

class DischargeToast internal constructor(context: Context, message: String, duration: Int) : Solvable {

  private val toast: Toast

  init {
    toast = Toast.makeText(context, message, duration)
  }

  override fun solve() {
    toast.show()
  }
}
