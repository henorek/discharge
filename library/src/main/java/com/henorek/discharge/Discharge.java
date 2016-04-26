package com.henorek.discharge;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class Discharge {

  private final Map<Class<?>, Solvable> dischargeBehaviors = new HashMap<>();

  // Discharge Singleton
  private final static Discharge INSTANCE = new Discharge();

  private Discharge() {
  }

  public static Discharge getInstance() {
    return INSTANCE;
  }

  /**
   *  Install library, from now on all UnhandledExceptions gonna be caught by Discharge.
  * */
  public void install(Context context) {
    //TODO tymczasowo uzywamy crashczyka jako supportu
    Crashczyk.install(context);
  }

  /**
   *  Define how Discharge have to behave when it encounters a exception.
   *
   *  @param problem exception class (must extends RuntimeException!) //TODO w sumie chuj wie czy musi
   *  @param solution handler for this exception
   *
   * */
  public void defineBehavior(Class<?> problem, Solvable solution) {
    dischargeBehaviors.put(problem, solution);
  }

  // Package restricted methods
  Solvable takeSolution(Class<?> model) {
    return dischargeBehaviors.get(model);
  }

  boolean isSolvable(Class<?> problem) {
    return dischargeBehaviors.containsKey(problem);
  }
}
