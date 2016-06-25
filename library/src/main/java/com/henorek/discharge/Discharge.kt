package com.henorek.discharge

import android.content.Context
import java.util.*

object Discharge {

  private val dischargeBehaviors = HashMap<Class<*>, Solvable>()

  /**
   * Install library, from now on all UnhandledExceptions gonna be caught by Discharge.
   */
  @JvmStatic fun install(context: Context) {
    Fuse.arm(context)
  }

  /**
   * Define how Discharge have to behave when it encounters a exception.

   * @param problem exception class (must extends RuntimeException!) //TODO w sumie chuj wie czy musi
   * *
   * @param solution handler for this exception
   * *
   * *
   */
  @JvmStatic fun defineBehavior(problem: Class<*>, solution: Solvable) {
    dischargeBehaviors.put(problem, solution)
  }

  internal fun takeSolution(problem: Class<*>): Solvable {
    return dischargeBehaviors[problem]!!
  }

  internal fun isSolvable(problem: Class<*>): Boolean {
    return dischargeBehaviors.containsKey(problem)
  }

}
