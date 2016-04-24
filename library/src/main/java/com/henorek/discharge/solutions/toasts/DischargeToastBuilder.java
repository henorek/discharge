package com.henorek.discharge.solutions.toasts;

import android.content.Context;

public class DischargeToastBuilder {

  private String message;
  private Context context;

  private DischargeToastBuilder(Context context) {
    this.context = context;
  }

  public static DischargeToastBuilder dischargeToast(Context context) {
    return new DischargeToastBuilder(context);
  }

  public DischargeToastBuilder withMessage(String message) {
    assert message != null;
    this.message = message;
    return this;
  }

  public DischargeToast build() {
    assert message != null;
    return new DischargeToast(context, message);
  }
}
