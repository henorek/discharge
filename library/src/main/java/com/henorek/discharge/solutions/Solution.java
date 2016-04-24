package com.henorek.discharge.solutions;

import com.henorek.discharge.Solvable;

public class Solution implements Solvable {

  Solvable solvable;
  String title;

  @Override public void solve() {
    solvable.solve();
  }
}
