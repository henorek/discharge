package com.henorek.discharge.solutions.builders

import android.content.Context

class DischargeToastBuilder internal constructor(private val context: Context) {

  private var message: String? = null
  private var duration: Int = 0

  fun withMessage(message: String?): DischargeToastBuilder {
    assert(message != null)
    this.message = message
    return this
  }

  fun withMessage(messageResourceId: Int): DischargeToastBuilder {
    this.message = context.resources.getString(messageResourceId)
    return this
  }

  fun withDuration(duration: Int): DischargeToastBuilder {
    this.duration = duration
    return this
  }

  fun build(): DischargeToast {
    return DischargeToast(context, message!!, duration)
  }
}
