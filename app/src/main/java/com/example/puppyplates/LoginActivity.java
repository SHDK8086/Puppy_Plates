package com.example.puppyplates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.puppyplates.databinding.LoginActivityBinding;

public class LoginActivity extends AppCompatActivity {

    // Declare variables for view binding and database helper.
    LoginActivityBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize view binding to access views in the layout.
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the DatabaseHelper instance.
        databaseHelper = new DatabaseHelper(this);

        // Set an OnClickListener on the login button to handle login logic.
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user inputs from the text fields.
                String email = binding.emailinput.getText().toString().trim();
                String password = binding.pwinput.getText().toString().trim();

                // Check if email or password fields are empty.
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Validate email and password against stored credentials.
                    Boolean checkCredentials = databaseHelper.checkemailpassword(email, password);

                    if (checkCredentials) {
                        // If credentials are correct, show a success message and navigate to the Dashboard.
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);

                        // Clear the input fields.
                        binding.emailinput.setText("");
                        binding.pwinput.setText("");
                    } else {
                        // If credentials are incorrect, show an error message.
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set an OnClickListener on the signup button to navigate to the SignUp activity.
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        // Find the admin login button and set an OnClickListener to navigate to the AdminLogin activity.
        Button adminlogin = findViewById(R.id.adminlogin);
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AdminLogin.class);
                startActivity(intent);
            }
        });
    }
}
