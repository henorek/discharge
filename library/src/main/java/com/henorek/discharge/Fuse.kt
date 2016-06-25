package com.henorek.discharge

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.henorek.discharge.views.DefaultErrorActivity
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.ref.WeakReference
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.ZipFile

/**
 * @author Jarosław Jankowski <jarosz1994@gmail.com>
 */
object Fuse {

  private lateinit var application: Application
  private var lastActivityCreated = WeakReference<Activity>(null)
  private var isInBackground = false

  // Constants
  private const val LOG_TAG = "Discharge"
  private const val INTENT_ACTION_ERROR_ACTIVITY = "com.henorek.discharge.ERROR"
  private const val INTENT_ACTION_RESTART_ACTIVITY = "com.henorek.discharge.RESTART"
  private const val DISCHARGE_HANDLER_PACKAGE_NAME = "com.henorek.discharge"
  private const val DEFAULT_HANDLER_PACKAGE_NAME = "com.android.internal.os"
  private const val MAX_STACK_TRACE_SIZE = 131071

  // Intent constants
  private const val EXTRA_RESTART_ACTIVITY_CLASS = "com.henorek.discharge.EXTRA_RESTART_ACTIVITY_CLASS"
  private const val EXTRA_SHOW_ERROR_DETAILS = "com.henorek.discharge.EXTRA_SHOW_ERROR_DETAILS"
  private const val EXTRA_STACK_TRACE = "com.henorek.discharge.EXTRA_STACK_TRACE"
  private const val EXTRA_IMAGE_DRAWABLE_ID = "com.henorek.discharge.EXTRA_IMAGE_DRAWABLE_ID"

  // Settings
  @JvmStatic var launchErrorActivityWhenInBackground = true
  @JvmStatic var showErrorDetails = true
  @JvmStatic var enableAppRestart = true
  @JvmStatic var defaultErrorActivityDrawableId = R.drawable.fuse_error_image
  @JvmStatic var errorActivityClass: Class<out Activity>? = null
  @JvmStatic var restartActivityClass: Class<out Activity>? = null

  @JvmStatic internal fun arm(context: Context) {
    val oldHandler = Thread.getDefaultUncaughtExceptionHandler()

    if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      Log.e(LOG_TAG, "Unfortunately API 13 and below is not supported.")
      return;
    }

    if (oldHandler.javaClass.name.startsWith(DISCHARGE_HANDLER_PACKAGE_NAME)) {
      Log.e(LOG_TAG, "Discharge is already installed.")
      return;
    }

    if (!oldHandler.javaClass.name.startsWith(DEFAULT_HANDLER_PACKAGE_NAME)) {
      Log.e(LOG_TAG, "You already have an UncaughtExceptionHandler, are you sure this is correct?" +
          "If you use ACRA, Crashlytics or similar libraries, you must initialize them AFTER Discharge!" +
          "Installing anyway, but your original handler will not be called.")
    }

    application = context.applicationContext as Application

    Thread.setDefaultUncaughtExceptionHandler(initFuseUncaughtExceptionHandler())

      application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        internal var currentlyStartedActivities = 0

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
          if (activity.javaClass != errorActivityClass) {
            lastActivityCreated = WeakReference(activity)
          }
        }

        override fun onActivityStarted(activity: Activity) {
          currentlyStartedActivities++
          isInBackground = currentlyStartedActivities == 0
          //Do nothing
        }

        override fun onActivityResumed(activity: Activity) {
          //Do nothing
        }

        override fun onActivityPaused(activity: Activity) {
          //Do nothing
        }

        override fun onActivityStopped(activity: Activity) {
          currentlyStartedActivities--
          isInBackground = currentlyStartedActivities == 0
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
          //Do nothing
        }

        override fun onActivityDestroyed(activity: Activity) {
          //Do nothing
        }
      })

    Log.i(LOG_TAG, "Discharge has been installed.")

  }

  /**
   * Given an Intent, returns several error details including the stack trace extra from the intent.

   * @param context A valid context. Must not be null.
   * *
   * @param intent  The Intent. Must not be null.
   * *
   * @return The full error details.
   */
  @JvmStatic fun getAllErrorDetailsFromIntent(context: Context, intent: Intent): String {
    //I don't think that this needs localization because it's a development string...

    val currentDate = Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    //Get build date
    val buildDateAsString = getBuildDateAsString(context, dateFormat)

    //Get app version
    val versionName = getVersionName(context)

    var errorDetails = ""

    errorDetails += "Build version: $versionName \n"
    errorDetails += "Build date: $buildDateAsString \n"
    errorDetails += "Current date: " + dateFormat.format(currentDate) + " \n"
    errorDetails += "Device: " + getDeviceModelName() + " \n\n"
    errorDetails += "Stack trace:  \n"
    errorDetails += getStackTraceFromIntent(intent)
    return errorDetails
  }

  /**
   * Given an Intent, returns the drawable id of the image to show on the default error activity.

   * @param intent The Intent. Must not be null.
   * *
   * @return The id of the drawable to use.
   */
  @JvmStatic fun getDefaultErrorActivityDrawableIdFromIntent(intent: Intent): Int {
    return intent.getIntExtra(EXTRA_IMAGE_DRAWABLE_ID, R.drawable.fuse_error_image)
  }

  /**
   * Given an Intent, returns the stack trace extra from it.

   * @param intent The Intent. Must not be null.
   * *
   * @return The stacktrace, or null if not provided.
   */
  @JvmStatic fun getStackTraceFromIntent(intent: Intent): String {
    return intent.getStringExtra(EXTRA_STACK_TRACE)
  }

  /**
   * Given an Intent, returns if the error details button should be displayed.

   * @param intent The Intent. Must not be null.
   * *
   * @return true if the button must be shown, false otherwise.
   */
  @JvmStatic fun isShowErrorDetailsFromIntent(intent: Intent): Boolean {
    return intent.getBooleanExtra(EXTRA_SHOW_ERROR_DETAILS, true)
  }

  /**
   * Given an Intent, returns the restart activity class extra from it.

   * @param intent The Intent. Must not be null.
   * *
   * @return The restart activity class, or null if not provided.
   */
  @Suppress("UNCHECKED_CAST")
  @JvmStatic fun getRestartActivityClassFromIntent(intent: Intent): Class<out Activity>? {
    val serializedClass = intent.getSerializableExtra(EXTRA_RESTART_ACTIVITY_CLASS)

    if (serializedClass != null && serializedClass is Class<*>) {
      return serializedClass as Class<out Activity>
    } else {
      return null
    }
  }

  /**
   * Given an Intent, restarts the app and launches a startActivity to that intent.
   * The flags NEW_TASK and CLEAR_TASK are set if the Intent does not have them, to ensure
   * the app stack is fully cleared.
   * Must only be used from your error activity.

   * @param activity The current error activity. Must not be null.
   * *
   * @param intent   The Intent. Must not be null.
   */
  @JvmStatic fun restartApplicationWithIntent(activity: Activity, intent: Intent) {
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    activity.finish()
    activity.startActivity(intent)
    killCurrentProcess()
  }

  /**
   * Closes the app. Must only be used from your error activity.

   * @param activity The current error activity. Must not be null.
   */
  @JvmStatic fun closeApplication(activity: Activity) {
    activity.finish()
    killCurrentProcess()
  }

  private fun initFuseUncaughtExceptionHandler(): Thread.UncaughtExceptionHandler {
    val handler: Thread.UncaughtExceptionHandler = Thread.UncaughtExceptionHandler { thread, throwable ->
      Log.e(LOG_TAG, "App has crashed, executing Fuse's UncaughtExceptionHandler", throwable)

      if (errorActivityClass == null) {
        errorActivityClass = guessErrorActivityClass(application);
      }

      if (isStackTraceLikelyConflictive(throwable, errorActivityClass)) {
        Log.e(LOG_TAG,
            "Your application class or your error activity have crashed, the custom activity will not be launched!");
      } else {
        if (launchErrorActivityWhenInBackground || !isInBackground) {
          val intent: Intent = Intent(application, errorActivityClass)
          val stringWriter: StringWriter = StringWriter()
          val printWriter: PrintWriter = PrintWriter(stringWriter)
          throwable.printStackTrace(printWriter)
          var stackTraceString = stringWriter.toString()

          if (stackTraceString.length > MAX_STACK_TRACE_SIZE) {
            val disclaimer = " [stack trace too large]"
            stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length) + disclaimer
          }

          if (enableAppRestart && restartActivityClass == null) {
            restartActivityClass = guessRestartActivityClass(application)
          } else if (!enableAppRestart) {
            restartActivityClass = null
          }

          intent.putExtra(EXTRA_STACK_TRACE, stackTraceString)
          intent.putExtra(EXTRA_RESTART_ACTIVITY_CLASS, restartActivityClass)
          intent.putExtra(EXTRA_SHOW_ERROR_DETAILS, showErrorDetails)
          intent.putExtra(EXTRA_IMAGE_DRAWABLE_ID, defaultErrorActivityDrawableId)
          intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
          application.startActivity(intent)
        }
      }
      val lastActivity: Activity? = lastActivityCreated.get();
      if (lastActivity != null) {
        lastActivity.finish();
        lastActivityCreated.clear();
      }
      killCurrentProcess();
    }
    return handler;
  }

  private fun killCurrentProcess() {
    android.os.Process.killProcess(android.os.Process.myPid())
    System.exit(10)
  }

  private fun isStackTraceLikelyConflictive(throwable: Throwable?, errorActivityClass: Class<out Activity>?): Boolean {
      val stackTrace = throwable!!.stackTrace
      for (element in stackTrace) {
        if (element.className == "android.app.ActivityThread" && element.methodName == "handleBindApplication" || element.className == errorActivityClass!!.name) {
          return true
        }
      }
    return false
  }

  private fun guessErrorActivityClass(context: Context): Class<out Activity> {
    var resolvedActivityClass: Class<out Activity>?
    resolvedActivityClass = getErrorActivityClassWithIntentFilter(context)
    if (resolvedActivityClass == null) {
      resolvedActivityClass = DefaultErrorActivity::class.java
    }
    return resolvedActivityClass
  }

  private fun guessRestartActivityClass(context: Context): Class<out Activity>? {
    var resolvedActivityClass: Class<out Activity>?
    resolvedActivityClass = getRestartActivityClassWithIntentFilter(context)
    if (resolvedActivityClass == null) {
      resolvedActivityClass = getLauncherActivity(context)
    }
    return resolvedActivityClass
  }

  @Suppress("UNCHECKED_CAST")
  private fun getErrorActivityClassWithIntentFilter(context: Context): Class<out Activity>? {
    val resolveInfos = context.packageManager.queryIntentActivities(
        Intent().setAction(INTENT_ACTION_ERROR_ACTIVITY),
        PackageManager.GET_RESOLVED_FILTER)
    if (resolveInfos != null && resolveInfos.size > 0) {
      val resolveInfo = resolveInfos[0]
      try {
        return Class.forName(resolveInfo.activityInfo.name) as Class<out Activity>
      } catch (e: ClassNotFoundException) {
        Log.e(LOG_TAG, "Failed when resolving the error activity class via intent filter, stack trace follows!", e)
      }
    }
    return null
  }

  @Suppress("UNCHECKED_CAST")
  private fun getRestartActivityClassWithIntentFilter(context: Context): Class<out Activity>? {
    val resolveInfos = context.packageManager.queryIntentActivities(
        Intent().setAction(INTENT_ACTION_RESTART_ACTIVITY),
        PackageManager.GET_RESOLVED_FILTER)

    if (resolveInfos != null && resolveInfos.size > 0) {
      val resolveInfo = resolveInfos[0]
      try {
        return Class.forName(resolveInfo.activityInfo.name) as Class<out Activity>
      } catch (e: ClassNotFoundException) {
        //Should not happen, print it to the log!
        Log.e(LOG_TAG, "Failed when resolving the restart activity class via intent filter, stack trace follows!", e)
      }

    }
    return null
  }

  @Suppress("UNCHECKED_CAST")
  private fun getLauncherActivity(context: Context): Class<out Activity>? {
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    if (intent != null) {
      try {
        return Class.forName(intent.component.className) as Class<out Activity>
      } catch (e: ClassNotFoundException) {
        Log.e(LOG_TAG, "Failed when resolving the restart activity class via getLaunchIntentForPackage, stack trace follows!", e)
      }
    }
    return null
  }

  private fun getBuildDateAsString(context: Context, dateFormat: DateFormat): String {
    val buildDate: String
    try {
      val ai = context.packageManager.getApplicationInfo(context.packageName, 0)
      val zf = ZipFile(ai.sourceDir)
      val ze = zf.getEntry("classes.dex")
      val time = ze.time
      buildDate = dateFormat.format(Date(time))
      zf.close()
    } catch (e: Exception) {
      buildDate = "Unknown"
    }

    return buildDate
  }

  private fun getVersionName(context: Context): String {
    try {
      val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
      return packageInfo.versionName
    } catch (e: Exception) {
      return "Unknown"
    }

  }

  private fun getDeviceModelName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    if (model.startsWith(manufacturer)) {
      return capitalize(model)
    } else {
      return capitalize(manufacturer) + " " + model
    }
  }

  private fun capitalize(s: String?): String {
    if (s == null || s.length == 0) {
      return ""
    }
    val first = s[0]
    if (Character.isUpperCase(first)) {
      return s
    } else {
      return Character.toUpperCase(first) + s.substring(1)
    }
  }

}