package com.henorek.discharge.solutions.builders;

import android.app.AlertDialog;
import com.henorek.discharge.Solvable;

public class DischargeDialog implements Solvable {

  private final AlertDialog dialog;

  DischargeDialog(AlertDialog dialog) {
    this.dialog = dialog;
  }

  @Override public void solve() {
    dialog.show();
  }
}

//TODO: pola w kostruktorze ustawiam a w solve() tylko show robie, bedzie w chuj konstruktorow zeby uwzglednic kazda mozliwosc
//TODO: archatypy są łatwe do rozjebania, po prostu zrobię modele z samymi danymi i dzieki metdzie withArchetype będę mógł podać archatyp i będzie sztosik