package com.petersburg_studio.cputest;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    double count = 0.0;
    int thread;
    EditText editText;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.number_of_threads);

    }

    public void onClickStart(View view) {
        runTest();
    }

    public void onClickMultipleThread(View view) {
        if (!editText.getText().toString().equals("")) {
            thread = Integer.parseInt(editText.getText().toString());
            if (thread > 0) {
                runTestMultipleThread();
            } else {
                snackbar = Snackbar.make(view, "Number must be > 0", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Close", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                snackbar.show();
            }
        } else {
            snackbar = Snackbar.make(view, "Edit text don't must be empty", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
            snackbar.show();
        }
    }

    private void runTest() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                while (count != 1.0) {
                    count += 1.0 / 10;
                }
                handler.postDelayed(this, 0);
            }
        });
    }

    private void runTestMultipleThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (count != 1.0) {
                    count += 1.0 / 10;
                    System.out.println("Value: " + count);
                }
            }
        });
    }
}
