package com.henorek.discharge.solutions.toasts;

import android.content.Context;
import android.widget.Toast;
import com.henorek.discharge.Solvable;

public class DischargeToast implements Solvable {

  private String message;
  private Context context;

  public DischargeToast(Context context) {
    this.message = "Some stock message, tbc";
    this.context = context;
  }

  DischargeToast(Context context, String message) {
    this.message = message;
    this.context = context;
  }

  @Override public void solve() {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }
}
