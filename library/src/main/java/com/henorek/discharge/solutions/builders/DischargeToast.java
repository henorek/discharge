package com.henorek.discharge.solutions.builders;

import android.content.Context;
import android.widget.Toast;
import com.henorek.discharge.R;
import com.henorek.discharge.Solvable;

// TODO: trzeba się zastanowic czy chce przyjmowac jakies defaulty czy moze wymuszamy pełne zbudowanie klasy (np razem z dlugoscia wyswietlanai toastu)
public class DischargeToast implements Solvable {

  private final Context context;

  private final String message;
  private final int duration;

  DischargeToast(final Context context, final String message, final int duration) {
    this.context = context;
    this.message = message;
    this.duration = duration;
  }

  @Override public void solve() {
    Toast.makeText(context, message, duration).show();
  }
}
