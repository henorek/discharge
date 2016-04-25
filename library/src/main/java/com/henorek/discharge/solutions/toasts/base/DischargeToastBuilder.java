package com.henorek.discharge.solutions.toasts.base;

import android.content.Context;

public class DischargeToastBuilder {

  private final Context context;

  private String message;
  private int duration;

  private DischargeToastBuilder(final Context context) {
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

  public DischargeToastBuilder withMessage(int messageResourceId) {
    this.message = context.getResources().getString(messageResourceId);
    return this;
  }

  public DischargeToastBuilder withDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public DischargeToast build() {
    assert message != null;
    return new DischargeToast(context, message, duration);
  }
}
