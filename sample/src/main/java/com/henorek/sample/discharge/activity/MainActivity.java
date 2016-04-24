/*
 * Copyright 2015 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.henorek.sample.discharge.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;
import com.henorek.discharge.Discharge;
import com.henorek.discharge.HandleException;
import com.henorek.discharge.Solvable;
import com.henorek.discharge.solutions.Solution;
import com.henorek.sample.discharge.exceptions.ArkaGdyniaKurwaSwiniaException;
import com.henorek.sample.discharge.R;

public class MainActivity extends Activity {

    @HandleException private void methodThatThrowsHandledException() {
        throw new ArkaGdyniaKurwaSwiniaException();
    }

    @HandleException private void methodThatThrowsUnhandledException() {
        throw new RuntimeException();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Discharge discharge = Discharge.getInstance();
        discharge.install(this);

        discharge.defineBehavior(ArkaGdyniaKurwaSwiniaException.class, new Solvable()  {
          @Override public void solve() {
            Toast.makeText(getBaseContext(), "Hej, zjebało się.",
                Toast.LENGTH_SHORT).show();
          }
        });

        Button crashWithHandledException = (Button) findViewById(R.id.crashWithHandledException);
        Button crashWithUnhandledException = (Button) findViewById(R.id.crashWithUnhandledException);

        crashWithHandledException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodThatThrowsHandledException();
            }
        });

        crashWithUnhandledException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodThatThrowsUnhandledException();
            }
        });

    }
}
