package com.henorek.discharge.solutions.builders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.henorek.discharge.Solvable;

public class DischargeDialog implements Solvable {

  private final Context context;

  private final String title;
  private final String message;

  DischargeDialog(final Context context, final String title, final String message) {
    this.context = context;
    this.title = title;
    this.message = message;
  }


  // TODO: To jest chujowe, w sensie tylko do testow, trzeba poprawic
  @Override public void solve() {
    new AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            // continue with delete
          }
        })
        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            // do nothing
          }
        })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
  }
}
