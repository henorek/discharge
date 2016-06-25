package com.henorek.discharge.solutions.builders

import android.app.AlertDialog
import com.henorek.discharge.Solvable

class DischargeDialog internal constructor(private val dialog: AlertDialog) : Solvable {

  override fun solve() {
    dialog.show()
  }
}

//TODO: pola w kostruktorze ustawiam a w solve() tylko show robie, bedzie w chuj konstruktorow zeby uwzglednic kazda mozliwosc
//TODO: archatypy są łatwe do rozjebania, po prostu zrobię modele z samymi danymi i dzieki metdzie withArchetype będę mógł podać archatyp i będzie sztosik