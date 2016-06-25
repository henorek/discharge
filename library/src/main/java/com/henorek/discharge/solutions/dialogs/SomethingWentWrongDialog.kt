package com.henorek.discharge.solutions.dialogs

import android.content.Context
import android.content.DialogInterface
import com.henorek.discharge.R
import com.henorek.discharge.Solvable
import com.henorek.discharge.solutions.builders.DischargeBuilder
import com.henorek.discharge.solutions.builders.DischargeDialog

class SomethingWentWrongDialog(context: Context) : Solvable {

  internal var dialog: DischargeDialog

  init {
    dialog = DischargeBuilder
        .dialog(context)
        .withTitle(R.string.discharge_something_went_wrong)
        .withPositiveButton(R.string.discharge_yes, DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        .build()
  }

  override fun solve() {
    dialog.solve()
  }
}
