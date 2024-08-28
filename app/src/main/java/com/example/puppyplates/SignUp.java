package com.example.puppyplates;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.puppyplates.databinding.SignupActivityBinding;

public class SignUp extends AppCompatActivity {

    // Declare variables for view binding and database helper.
    private SignupActivityBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize view binding to access views in the layout.
        binding = SignupActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the DatabaseHelper instance.
        databaseHelper = new DatabaseHelper(this);

        // Set an OnClickListener on the "Create Account" button to handle sign-up logic.
        binding.createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user inputs from the text fields.
                String email = binding.email.getText().toString().trim();
                String password = binding.signuppassword.getText().toString().trim();
                String confirmpassword = binding.confirmpassword.getText().toString().trim();

                // Check if any of the fields are empty.
                if (email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                    Toast.makeText(SignUp.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else if (password.equals(confirmpassword)) {
                    // Check if the email is already registered.
                    boolean checkemail = databaseHelper.checkemail(email);
                    Log.d("SignUp", "Check email exists: " + checkemail);

                    if (!checkemail) {
                        // Insert the new user's data into the database.
                        boolean insert = databaseHelper.insertdata(email, password);
                        Log.d("SignUp", "Insert data result: " + insert);

                        if (insert) {
                            // If insertion is successful, show a success message and navigate to the LoginActivity.
                            Toast.makeText(SignUp.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If insertion fails, show a failure message.
                            Toast.makeText(SignUp.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // If the email is already registered, show an error message.
                        Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // If the passwords do not match, show an error message.
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
