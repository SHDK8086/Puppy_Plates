package com.example.puppyplates;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Accessorriescategory extends AppCompatActivity {

    // Declare UI elements
    private LinearLayout productLayout;
    private EditText searchBar;

    // Declare CardView elements for each product
    private CardView productCard17, productCard18, productCard19, productCard20;
    private TextView product_name17, product_name18, product_name19, product_name20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessoriescategory_activity);

        // Initialize UI elements
        searchBar = findViewById(R.id.search_bar);
        productLayout = findViewById(R.id.productLayout);

        // Initialize CardViews and TextViews
        productCard17 = findViewById(R.id.productCard17);
        productCard18 = findViewById(R.id.productCard18);
        productCard19 = findViewById(R.id.productCard19);
        productCard20 = findViewById(R.id.productCard20);

        product_name17 = findViewById(R.id.product_name17);
        product_name18 = findViewById(R.id.product_name18);
        product_name19 = findViewById(R.id.product_name19);
        product_name20 = findViewById(R.id.product_name20);

        // Setup click listeners for product buttons
        setupProductButtons();

        // Add a TextWatcher to the search bar to filter products as the user types
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filter products based on the search query
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed after text changes
            }
        });
    }

    // Setup click listeners for product buttons to add items to the cart
    private void setupProductButtons() {
        Button product17Button = findViewById(R.id.product17);
        Button product18Button = findViewById(R.id.product18);
        Button product19Button = findViewById(R.id.product19);
        Button product20Button = findViewById(R.id.product20);

        // Assign actions to each button
        product17Button.setOnClickListener(v -> addToCart("Orthopedic Dog Bed", 120));
        product18Button.setOnClickListener(v -> addToCart("Adjustable Dog Harness", 70));
        product19Button.setOnClickListener(v -> addToCart("Interactive Dog Toy", 30));
        product20Button.setOnClickListener(v -> addToCart("Travel Dog Water Bottle", 20));
    }

    // Add selected product to the cart using SharedPreferences
    private void addToCart(String name, int price) {
        // Get shared preferences for cart data storage
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Create a key for the item and store its details in shared preferences
        String itemKey = name + "-";
        editor.putString(itemKey, price + "|1"); // Storing price and initial quantity (1)
        editor.apply(); // Apply changes to shared preferences

        // Show a confirmation toast
        Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // Filter products based on the search query
    private void filterProducts(String query) {
        // Get the first letter of the search query and convert it to uppercase
        String firstLetter = query.length() > 0 ? query.substring(0, 1).toUpperCase() : "";

        // Set visibility of product cards based on whether their names start with the search query
        productCard17.setVisibility(product_name17.getText().toString().toUpperCase().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard18.setVisibility(product_name18.getText().toString().toUpperCase().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard19.setVisibility(product_name19.getText().toString().toUpperCase().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard20.setVisibility(product_name20.getText().toString().toUpperCase().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
    }
}
