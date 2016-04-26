package com.henorek.discharge.solutions.builders;

import android.content.Context;

public class DischargeDialogBuilder {

  private final Context context;

  private String message;

  DischargeDialogBuilder(final Context context) {
    this.context = context;
  }

  public DischargeDialogBuilder withMessage(String message) {
    assert message != null;
    this.message = message;
    return this;
  }

  public DischargeDialogBuilder withMessage(int messageResourceId) {
    this.message = context.getResources().getString(messageResourceId);
    return this;
  }

  public DischargeDialog build() {
    assert message != null;
    return new DischargeDialog(context, "Tymczasowo" ,message);
  }
}
