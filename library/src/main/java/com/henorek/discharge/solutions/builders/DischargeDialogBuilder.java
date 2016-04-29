package com.henorek.discharge.solutions.builders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

public class DischargeDialogBuilder {

  private AlertDialog.Builder dialog;

  DischargeDialogBuilder(final Context context) {
    dialog = new AlertDialog.Builder(context);
  }

  public DischargeDialogBuilder withTitle(int titleResourceId) {
    dialog.setTitle(titleResourceId);
    return this;
  }

  public DischargeDialogBuilder withTitle(CharSequence title) {
    dialog.setTitle(title);
    return this;
  }

  public DischargeDialogBuilder withCustomTitle(View customTitleView) {
    dialog.setCustomTitle(customTitleView);
    return this;
  }

  public DischargeDialogBuilder withMessage(int messageResourceId) {
    dialog.setMessage(messageResourceId);
    return this;
  }

  public DischargeDialogBuilder withMessage(CharSequence message) {
    dialog.setMessage(message);
    return this;
  }

  public DischargeDialogBuilder withIcon(int iconResourceId) {
    dialog.setIcon(iconResourceId);
    return this;
  }

  public DischargeDialogBuilder withIcon(Drawable icon) {
    dialog.setIcon(icon);
    return this;
  }

  public DischargeDialogBuilder withIconAttribute(int attrResourceId) {
    if (Build.VERSION.SDK_INT > 11) dialog.setIconAttribute(attrResourceId);
    return this;
  }

  public DischargeDialogBuilder withPositiveButton(int textResourceId, DialogInterface.OnClickListener listener) {
    dialog.setPositiveButton(textResourceId, listener);
    return this;
  }

  public DischargeDialogBuilder withPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
    dialog.setPositiveButton(text, listener);
    return this;
  }

  public DischargeDialogBuilder withNegativeButton(int textResourceId, DialogInterface.OnClickListener listener) {
    dialog.setNegativeButton(textResourceId, listener);
    return this;
  }

  public DischargeDialogBuilder withNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
    dialog.setNegativeButton(text, listener);
    return this;
  }

  public DischargeDialogBuilder withNeutralButton(int textResourceId, DialogInterface.OnClickListener listener) {
    dialog.setNeutralButton(textResourceId, listener);
    return this;
  }

  public DischargeDialogBuilder withNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
    dialog.setNeutralButton(text, listener);
    return this;
  }

  public DischargeDialogBuilder withCancelable(boolean cancelable) {
    dialog.setCancelable(cancelable);
    return this;
  }

  public DischargeDialogBuilder withOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
    dialog.setOnCancelListener(onCancelListener);
    return this;
  }

  public DischargeDialogBuilder withOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
    if (Build.VERSION.SDK_INT > 17) dialog.setOnDismissListener(onDismissListener);
    return this;
  }

  public DischargeDialogBuilder withOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
    dialog.setOnKeyListener(onKeyListener);
    return this;
  }

  public DischargeDialogBuilder withItems(int itemsResourceId, DialogInterface.OnClickListener listener) {
    dialog.setItems(itemsResourceId, listener);
    return this;
  }

  public DischargeDialogBuilder withItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
    dialog.setItems(items, listener);
    return this;
  }

  public DischargeDialogBuilder withAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
    dialog.setAdapter(adapter, listener);
    return this;
  }

  public DischargeDialogBuilder withCursor(Cursor cursor, DialogInterface.OnClickListener listener,
      String labelColumn) {
    dialog.setCursor(cursor, listener, labelColumn);
    return this;
  }

  public DischargeDialogBuilder withMultiChoiceItems(int itemsId, boolean[] checkedItems,
      DialogInterface.OnMultiChoiceClickListener listener) {
    dialog.setMultiChoiceItems(itemsId, checkedItems, listener);
    return this;
  }

  public DischargeDialogBuilder withMultiChoiceItems(CharSequence[] items, boolean[] checkedItems,
      DialogInterface.OnMultiChoiceClickListener listener) {
    dialog.setMultiChoiceItems(items, checkedItems, listener);
    return this;
  }

  public DischargeDialogBuilder withMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn,
      DialogInterface.OnMultiChoiceClickListener listener) {
    dialog.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
    return this;
  }

  public DischargeDialogBuilder withSingleChoiceItems(int itemsId, int checkedItem,
      DialogInterface.OnClickListener listener) {
    dialog.setSingleChoiceItems(itemsId, checkedItem, listener);
    return this;
  }

  public DischargeDialogBuilder withSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn,
      DialogInterface.OnClickListener listener) {
    dialog.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
    return this;
  }

  public DischargeDialogBuilder withSingleChoiceItems(CharSequence[] items, int checkedItem,
      DialogInterface.OnClickListener listener) {
    dialog.setSingleChoiceItems(items, checkedItem, listener);
    return this;
  }

  public DischargeDialogBuilder withSingleChoiceItems(ListAdapter adapter, int checkedItem,
      DialogInterface.OnClickListener listener) {
    dialog.setSingleChoiceItems(adapter, checkedItem, listener);
    return this;
  }

  public DischargeDialogBuilder withOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
    dialog.setOnItemSelectedListener(listener);
    return this;
  }

  public DischargeDialogBuilder withView(int layoutResId) {
    if (Build.VERSION.SDK_INT > 21) dialog.setView(layoutResId);
    return this;
  }

  public DischargeDialogBuilder withView(View view) {
    dialog.setView(view);
    return this;
  }

  public DischargeDialog build() {
    return new DischargeDialog(dialog.create());
  }
}
