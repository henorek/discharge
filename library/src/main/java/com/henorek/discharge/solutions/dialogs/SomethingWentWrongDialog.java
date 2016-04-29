package com.henorek.discharge.solutions.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import com.henorek.discharge.R;
import com.henorek.discharge.Solvable;
import com.henorek.discharge.solutions.builders.DischargeBuilder;
import com.henorek.discharge.solutions.builders.DischargeDialog;

public class SomethingWentWrongDialog implements Solvable {

  DischargeDialog dialog;

  public SomethingWentWrongDialog(Context context) {
    dialog = DischargeBuilder.dialog(context)
        .withTitle(R.string.discharge_something_went_wrong)
        .withPositiveButton(R.string.discharge_yes, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        })
        .build();
  }

  @Override public void solve() {
    dialog.solve();
  }
}
