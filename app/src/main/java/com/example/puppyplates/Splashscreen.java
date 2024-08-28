package com.example.puppyplates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {

    // Constant defining the duration of the splash screen (3000 milliseconds or 3 seconds).
    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the content view to the layout specified in `splash_activity.xml`.
        setContentView(R.layout.splash_activity);

        // Creates a new Handler that will execute a Runnable after a delay (SPLASH_TIME_OUT).
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent to start the LoginActivity after the splash screen.
                Intent intent = new Intent(Splashscreen.this, LoginActivity.class);
                startActivity(intent); // Start the LoginActivity.
                finish(); // Close the Splashscreen activity so it is removed from the back stack.
            }
        }, SPLASH_TIME_OUT); // The delay duration before the Runnable is executed.
    }
}
