package com.example.puppyplates;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextInputEditText username, fullname, phoneno, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views by their IDs
        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        phoneno = findViewById(R.id.phoneno);
        address = findViewById(R.id.address);

        MaterialButton submitButton = findViewById(R.id.submitbtn);
        MaterialButton logoutButton = findViewById(R.id.logoutbtn);

        // Load saved profile data
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String savedFullName = sharedPreferences.getString("fullName", "");
        fullname.setText(savedFullName);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String fullName = fullname.getText().toString().trim();
                String phoneNo = phoneno.getText().toString().trim();
                String userAddress = address.getText().toString().trim();

                if (email.isEmpty() || fullName.isEmpty() || phoneNo.isEmpty() || userAddress.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUpdated = databaseHelper.insertProfileData(email, fullName, phoneNo, userAddress);
                if (isUpdated) {
                    // Save the full name in SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fullName", fullName);
                    editor.apply();

                    Toast.makeText(ProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Profile Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // This will remove all stored data
                editor.apply();

                // Handle session termination if applicable
                Toast.makeText(ProfileActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}