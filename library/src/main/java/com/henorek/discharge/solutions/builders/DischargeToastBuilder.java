package com.henorek.discharge.solutions.builders;

import android.content.Context;

public class DischargeToastBuilder {

  private final Context context;

  private String message;
  private int duration;

  DischargeToastBuilder(final Context context) {
    this.context = context;
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
