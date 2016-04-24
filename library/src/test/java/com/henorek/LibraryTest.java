package com.henorek;

import android.annotation.TargetApi;
import android.os.Build;
import com.henorek.discharge.BuildConfig;
import com.henorek.discharge.Discharge;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)

@Config(sdk = 21, manifest = "src/main/AndroidManifest.xml",constants = BuildConfig.class)
@RunWith(RobolectricGradleTestRunner.class)
public class LibraryTest {

  Discharge discharge;
  boolean handled = false;

  @Before
  public void setup() {
    discharge = Discharge.getInstance();
    //discharge.defineBehavior(TestException.class, handled);
  }

  @Test
  public void testMethodThatAddsTwoNumbers() {
    //throw new TestException();
    assertEquals(true, handled);
  }
}
