package com.henorek.discharge.solutions.builders

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.ListAdapter

class DischargeDialogBuilder internal constructor(context: Context) {

  private val dialog: AlertDialog.Builder

  init {
    dialog = AlertDialog.Builder(context)
  }

  fun withTitle(titleResourceId: Int): DischargeDialogBuilder {
    dialog.setTitle(titleResourceId)
    return this
  }

  fun withTitle(title: CharSequence): DischargeDialogBuilder {
    dialog.setTitle(title)
    return this
  }

  fun withCustomTitle(customTitleView: View): DischargeDialogBuilder {
    dialog.setCustomTitle(customTitleView)
    return this
  }

  fun withMessage(messageResourceId: Int): DischargeDialogBuilder {
    dialog.setMessage(messageResourceId)
    return this
  }

  fun withMessage(message: CharSequence): DischargeDialogBuilder {
    dialog.setMessage(message)
    return this
  }

  fun withIcon(iconResourceId: Int): DischargeDialogBuilder {
    dialog.setIcon(iconResourceId)
    return this
  }

  fun withIcon(icon: Drawable): DischargeDialogBuilder {
    dialog.setIcon(icon)
    return this
  }

  fun withIconAttribute(attrResourceId: Int): DischargeDialogBuilder {
    if (Build.VERSION.SDK_INT > 11) dialog.setIconAttribute(attrResourceId)
    return this
  }

  fun withPositiveButton(textResourceId: Int, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setPositiveButton(textResourceId, listener)
    return this
  }

  fun withPositiveButton(text: CharSequence, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setPositiveButton(text, listener)
    return this
  }

  fun withNegativeButton(textResourceId: Int, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setNegativeButton(textResourceId, listener)
    return this
  }

  fun withNegativeButton(text: CharSequence, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setNegativeButton(text, listener)
    return this
  }

  fun withNeutralButton(textResourceId: Int, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setNeutralButton(textResourceId, listener)
    return this
  }

  fun withNeutralButton(text: CharSequence, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setNeutralButton(text, listener)
    return this
  }

  fun withCancelable(cancelable: Boolean): DischargeDialogBuilder {
    dialog.setCancelable(cancelable)
    return this
  }

  fun withOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): DischargeDialogBuilder {
    dialog.setOnCancelListener(onCancelListener)
    return this
  }

  fun withOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): DischargeDialogBuilder {
    if (Build.VERSION.SDK_INT > 17) dialog.setOnDismissListener(onDismissListener)
    return this
  }

  fun withOnKeyListener(onKeyListener: DialogInterface.OnKeyListener): DischargeDialogBuilder {
    dialog.setOnKeyListener(onKeyListener)
    return this
  }

  fun withItems(itemsResourceId: Int, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setItems(itemsResourceId, listener)
    return this
  }

  fun withItems(items: Array<CharSequence>, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setItems(items, listener)
    return this
  }

  fun withAdapter(adapter: ListAdapter, listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setAdapter(adapter, listener)
    return this
  }

  fun withCursor(cursor: Cursor, listener: DialogInterface.OnClickListener,
      labelColumn: String): DischargeDialogBuilder {
    dialog.setCursor(cursor, listener, labelColumn)
    return this
  }

  fun withMultiChoiceItems(itemsId: Int, checkedItems: BooleanArray,
      listener: DialogInterface.OnMultiChoiceClickListener): DischargeDialogBuilder {
    dialog.setMultiChoiceItems(itemsId, checkedItems, listener)
    return this
  }

  fun withMultiChoiceItems(items: Array<CharSequence>, checkedItems: BooleanArray,
      listener: DialogInterface.OnMultiChoiceClickListener): DischargeDialogBuilder {
    dialog.setMultiChoiceItems(items, checkedItems, listener)
    return this
  }

  fun withMultiChoiceItems(cursor: Cursor, isCheckedColumn: String, labelColumn: String,
      listener: DialogInterface.OnMultiChoiceClickListener): DischargeDialogBuilder {
    dialog.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener)
    return this
  }

  fun withSingleChoiceItems(itemsId: Int, checkedItem: Int,
      listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setSingleChoiceItems(itemsId, checkedItem, listener)
    return this
  }

  fun withSingleChoiceItems(cursor: Cursor, checkedItem: Int, labelColumn: String,
      listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener)
    return this
  }

  fun withSingleChoiceItems(items: Array<CharSequence>, checkedItem: Int,
      listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setSingleChoiceItems(items, checkedItem, listener)
    return this
  }

  fun withSingleChoiceItems(adapter: ListAdapter, checkedItem: Int,
      listener: DialogInterface.OnClickListener): DischargeDialogBuilder {
    dialog.setSingleChoiceItems(adapter, checkedItem, listener)
    return this
  }

  fun withOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener): DischargeDialogBuilder {
    dialog.setOnItemSelectedListener(listener)
    return this
  }

  fun withView(layoutResId: Int): DischargeDialogBuilder {
    if (Build.VERSION.SDK_INT > 21) dialog.setView(layoutResId)
    return this
  }

  fun withView(view: View): DischargeDialogBuilder {
    dialog.setView(view)
    return this
  }

  fun build(): DischargeDialog {
    return DischargeDialog(dialog.create())
  }
}
