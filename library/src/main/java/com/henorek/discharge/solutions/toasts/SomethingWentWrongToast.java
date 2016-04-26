package com.henorek.discharge.solutions.toasts;

import android.content.Context;
import android.widget.Toast;
import com.henorek.discharge.R;
import com.henorek.discharge.Solvable;
import com.henorek.discharge.solutions.builders.DischargeBuilder;
import com.henorek.discharge.solutions.builders.DischargeToast;

public class SomethingWentWrongToast implements Solvable {

  private final DischargeToast toast;

  public SomethingWentWrongToast(final Context context) {
    toast = DischargeBuilder
        .toast(context)
        .withMessage(R.string.discharge_something_went_wrong)
        .withDuration(Toast.LENGTH_LONG)
        .build();
  }

  @Override public void solve() {
    toast.solve();
  }
}
