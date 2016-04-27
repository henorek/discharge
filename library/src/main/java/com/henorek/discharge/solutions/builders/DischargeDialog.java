package com.henorek.discharge.solutions.builders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.henorek.discharge.Solvable;

public class DischargeDialog implements Solvable {

  private final AlertDialog.Builder dialog;

  DischargeDialog(final Context context, final String title, final int iconResourceId, final String message, final String positiveActionMessage, final DialogInterface.OnClickListener positiveActionEvent, final String neutralActionMessage, final DialogInterface.OnClickListener neutralActionEvent, final String negativeActionMessage, final DialogInterface.OnClickListener negativeActionEvent) {
    this(context, title, message, positiveActionMessage, positiveActionEvent, neutralActionMessage, neutralActionEvent, negativeActionMessage, negativeActionEvent);
    dialog.setIcon(iconResourceId);
  }

  DischargeDialog(final Context context, final String title, final String message, final String positiveActionMessage, final DialogInterface.OnClickListener positiveActionEvent, final String neutralActionMessage, final DialogInterface.OnClickListener neutralActionEvent, final String negativeActionMessage, final DialogInterface.OnClickListener negativeActionEvent) {
    this(context, title, message, positiveActionMessage, positiveActionEvent, neutralActionMessage, neutralActionEvent);
    dialog.setNegativeButton(negativeActionMessage, negativeActionEvent);
  }

  DischargeDialog(final Context context, final String title, final int iconResourceId, final String message, final String positiveActionMessage, final DialogInterface.OnClickListener positiveActionEvent, final String neutralActionMessage, final DialogInterface.OnClickListener neutralActionEvent) {
    this(context, title, iconResourceId, message, positiveActionMessage, positiveActionEvent);
    dialog.setNeutralButton(neutralActionMessage, neutralActionEvent);
  }

  DischargeDialog(final Context context, final String title, final String message, final String positiveActionMessage, final DialogInterface.OnClickListener positiveActionEvent, final String neutralActionMessage, final DialogInterface.OnClickListener neutralActionEvent) {
    this(context, title, message, positiveActionMessage, positiveActionEvent);
    dialog.setNeutralButton(neutralActionMessage, neutralActionEvent);
  }

  DischargeDialog(final Context context, final String title, final int iconResourceId, final String message, final String positiveActionMessage, DialogInterface.OnClickListener positiveActionEvent) {
    this(context, title, message, positiveActionMessage, positiveActionEvent);
    dialog.setIcon(iconResourceId);
  }

  DischargeDialog(final Context context, final String title, final String message, final String positiveActionMessage, DialogInterface.OnClickListener positiveActionEvent) {
    this(context, title, message);
    dialog.setPositiveButton(positiveActionMessage, positiveActionEvent);
  }

  DischargeDialog(final Context context, final String title, final String message, final int iconResourceId) {
    this(context, title, message);
    dialog.setIcon(iconResourceId);
  }

  DischargeDialog(final Context context, final String title, final String message) {
    dialog = new AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message);
  }

  @Override public void solve() {
    dialog.show();
  }
}

//TODO: pola w kostruktorze ustawiam a w solve() tylko show robie, bedzie w chuj konstruktorow zeby uwzglednic kazda mozliwosc
//TODO: archatypy są łatwe do rozjebania, po prostu zrobię modele z samymi danymi i dzieki metdzie withArchetype będę mógł podać archatyp i będzie sztosik