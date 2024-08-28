package com.example.puppyplates;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        // Load user's full name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullName", "to PuppyPlates");

        // Set the TextView to display the full name
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        if (welcomeTextView != null) {
            welcomeTextView.setText("Welcome, " + fullName);
        }

        // Set up click listeners for category image views
        setupCategoryNavigation();

        // Set up bottom navigation
        setupBottomNavigation();

        // Set up SetupADbuttonNavigation for AD views
        SetupADbuttonNavigation();
    }

    private void setupCategoryNavigation() {
        ImageView kibbleimg = findViewById(R.id.kibbleimg);
        if (kibbleimg != null) {
            kibbleimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, Kibblecategory.class);
                    startActivity(intent);
                }
            });
        }

        ImageView cannedimg = findViewById(R.id.cannedimg);
        if (cannedimg != null) {
            cannedimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, Cannedcategory.class);
                    startActivity(intent);
                }
            });
        }

        ImageView supplimentimg = findViewById(R.id.supplimentimg);
        if (supplimentimg != null) {
            supplimentimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, Supplimentcategory.class);
                    startActivity(intent);
                }
            });
        }

        ImageView treatimg = findViewById(R.id.treatimg);
        if (treatimg != null) {
            treatimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, Treatscategory.class);
                    startActivity(intent);
                }
            });
        }

        ImageView accessoriesimg = findViewById(R.id.accessoriesimg);
        if (accessoriesimg != null) {
            accessoriesimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, Accessorriescategory.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    int itemId = item.getItemId();

                    if (itemId == R.id.bottomnav){
                        return true;
                    } else if (itemId == R.id.home) {
                        Intent intent = new Intent(Dashboard.this, Dashboard.class);
                        startActivity(intent);
                        return true;

                    }else if (itemId == R.id.healthtips){
                        Intent intent = new Intent(Dashboard.this, HealthTips.class);
                        startActivity(intent);
                        return true;

                    } else if (itemId == R.id.cart) {
                        Intent intent = new Intent(Dashboard.this, Cart.class);
                        startActivity(intent);
                        return true;
                        
                    } else if (itemId== R.id.profile) {
                        Intent intent = new Intent(Dashboard.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;

                    } else if (itemId== R.id.support) {
                        Intent intent = new Intent(Dashboard.this, AboutUs.class);
                        startActivity(intent);
                        return true;

                    }
                    return false;
                }
            });
        }
    }
    private void SetupADbuttonNavigation(){
        Button aboutus = findViewById(R.id.aboutus);
        if (aboutus != null) {
            aboutus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Dashboard.this, AboutUs.class);
                    startActivity(intent);
                }
            });
        }
    }
}
