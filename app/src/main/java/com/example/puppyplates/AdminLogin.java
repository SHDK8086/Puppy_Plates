package com.example.puppyplates;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdminLogin extends AppCompatActivity {

    // Declare UI elements for username and password input fields, and login button
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin_activity);

        // Initialize the UI elements
        usernameInput = findViewById(R.id.usenameinput);
        passwordInput = findViewById(R.id.pwinput);
        loginButton = findViewById(R.id.login);

        // Set up a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entered username and password, trimming any extra spaces
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // Check if either the username or password fields are empty
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    // Display a toast message prompting the user to enter both fields
                    Toast.makeText(AdminLogin.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Hardcoded admin credentials check
                    if (username.equals("Admin") && password.equals("Admin123")) {
                        // If the credentials match, show a success message
                        Toast.makeText(AdminLogin.this, "Login successful", Toast.LENGTH_SHORT).show();

                        // Intent to navigate to the AdminDashboard activity after successful login
                        Intent intent = new Intent(AdminLogin.this, AdminDashboard.class);
                        startActivity(intent);
                        finish(); // Finish the current activity so that the user cannot return to it
                    } else {
                        // If the credentials are incorrect, display an error message
                        Toast.makeText(AdminLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
