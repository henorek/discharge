package com.henorek.discharge.solutions.builders;

import android.content.Context;
import android.widget.Toast;
import com.henorek.discharge.Solvable;

public class DischargeToast implements Solvable {

  private final Toast toast;

  DischargeToast(final Context context, final String message, final int duration) {
    toast = Toast.makeText(context, message, duration);
  }

  @Override public void solve() {
    toast.show();
  }
}
