package com.henorek;

import android.annotation.TargetApi;
import android.os.Build;
import com.henorek.discharge.BuildConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)

@Config(sdk = 21, manifest = "src/main/AndroidManifest.xml",constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class LibraryTest {

  boolean handled = false;

  @Before
  public void setup() {
  }

  @Test
  public void testMethodThatAddsTwoNumbers() {
    assertEquals(true, handled);
  }
}
