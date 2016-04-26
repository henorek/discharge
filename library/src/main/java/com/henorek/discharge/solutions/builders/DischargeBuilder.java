package com.henorek.discharge.solutions.builders;

import android.content.Context;

public class DischargeBuilder {

  public static DischargeToastBuilder toast(Context context) {
    return new DischargeToastBuilder(context);
  }

  public static DischargeDialogBuilder dialog(Context context) {
    return new DischargeDialogBuilder(context);
  }
}
