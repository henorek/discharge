package com.henorek.discharge.solutions.builders

import android.content.Context

object DischargeBuilder {

  @JvmStatic fun toast(context: Context): DischargeToastBuilder {
    return DischargeToastBuilder(context)
  }

  @JvmStatic fun dialog(context: Context): DischargeDialogBuilder {
    return DischargeDialogBuilder(context)
  }
}
