package com.henorek.discharge.solutions.builders;

import android.content.Context;
import android.content.DialogInterface;

public class DischargeDialogBuilder {

  private final Context context;

  private String title;
  private int icon;
  private String message;

  private String positiveActionMessage, neutralActionMessage, negativeActionMessage;
  private DialogInterface.OnClickListener positiveActionEvent, neutralActionEvent, negativeActionEvent;

  DischargeDialogBuilder(final Context context) {
    this.context = context;
  }

  public DischargeDialogBuilder withTitle(String title) {
    assert title != null;
    this.title = title;
    return this;
  }

  public DischargeDialogBuilder withTitle(int titleResourceId) {
    this.title = context
        .getResources()
        .getString(titleResourceId);
    return this;
  }

  public DischargeDialogBuilder withIcon(int iconResourceId) {
    this.icon = iconResourceId;
    return this;
  }

  public DischargeDialogBuilder withMessage(String message) {
    assert message != null;
    this.message = message;
    return this;
  }

  public DischargeDialogBuilder withMessage(int messageResourceId) {
    this.message = context
        .getResources()
        .getString(messageResourceId);
    return this;
  }

  public DischargeDialogBuilder withPositiveButton(String positiveActionMessage, DialogInterface.OnClickListener positiveActionEvent) {
    assert positiveActionMessage != null;
    assert positiveActionEvent != null;
    this.positiveActionMessage = positiveActionMessage;
    this.positiveActionEvent = positiveActionEvent;
    return this;
  }

  public DischargeDialogBuilder withPositiveButton(int positiveActionMessageResourceId, DialogInterface.OnClickListener positiveActionEvent) {
    assert positiveActionMessage != null;
    assert positiveActionEvent != null;
    this.positiveActionMessage = context
        .getResources()
        .getString(positiveActionMessageResourceId);
    this.positiveActionEvent = positiveActionEvent;
    return this;
  }

  public DischargeDialogBuilder withNeutralButton(String neutralActionMessage, DialogInterface.OnClickListener neutralActionEvent) {
    assert neutralActionMessage != null;
    assert neutralActionEvent != null;
    this.neutralActionMessage = neutralActionMessage;
    this.neutralActionEvent = neutralActionEvent;
    return this;
  }

  public DischargeDialogBuilder withNeutralButton(int neutralActionMessageResourceId, DialogInterface.OnClickListener neutralActionEvent) {
    assert neutralActionMessage != null;
    assert neutralActionEvent != null;
    this.neutralActionMessage = context
        .getResources()
        .getString(neutralActionMessageResourceId);
    this.neutralActionEvent = neutralActionEvent;
    return this;
  }

  public DischargeDialogBuilder withNegativeButton(String negativeActionMessage, DialogInterface.OnClickListener negativeActionEvent) {
    assert negativeActionMessage != null;
    assert negativeActionEvent != null;
    this.negativeActionMessage = negativeActionMessage;
    this.negativeActionEvent = negativeActionEvent;
    return this;
  }

  public DischargeDialogBuilder withNegativeButton(int negativeActionMessageResourceId, DialogInterface.OnClickListener negativeActionEvent) {
    assert negativeActionMessage != null;
    assert negativeActionEvent != null;
    this.negativeActionMessage = context
        .getResources()
        .getString(negativeActionMessageResourceId);
    this.negativeActionEvent = negativeActionEvent;
    return this;
  }

  public DischargeDialog build() {
    int eventsAmount = getEventsAmountByFields();
    if (icon != 0) {
      switch (eventsAmount) {
        case 1:
          return new DischargeDialog(context, title, icon, message, positiveActionMessage, positiveActionEvent);
        case 2:
          return new DischargeDialog(context, title, icon, message, positiveActionMessage, positiveActionEvent, neutralActionMessage, neutralActionEvent);
        case 3:
          return new DischargeDialog(context, title, icon, message, positiveActionMessage, positiveActionEvent, neutralActionMessage, neutralActionEvent, negativeActionMessage, negativeActionEvent);
      }
    } else {
      switch (eventsAmount) {
        case 1:
          return new DischargeDialog(context, title, message, positiveActionMessage, positiveActionEvent);
        case 2:
          return new DischargeDialog(context, title, message, positiveActionMessage, positiveActionEvent, neutralActionMessage, neutralActionEvent);
        case 3:
          return new DischargeDialog(context, title, message, positiveActionMessage, positiveActionEvent, neutralActionMessage, neutralActionEvent, negativeActionMessage, negativeActionEvent);
      }
    }
    return new DischargeDialog(context, title, message);
  }

  private int getEventsAmountByFields() {
    int eventsAmount = 0;
    if (positiveActionEvent != null) eventsAmount++;
    if (neutralActionEvent != null) eventsAmount++;
    if (negativeActionEvent != null) eventsAmount++;
    return eventsAmount;
  }
}
