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

public class Treatscategory extends AppCompatActivity {

    private LinearLayout productLayout;
    private EditText searchBar;

    private CardView productCard13, productCard14, productCard15, productCard16;
    private TextView product_name13, product_name14, product_name15, product_name16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout file 'treatscategory_activity.xml'
        setContentView(R.layout.treatscategory_activity);

        // Initialize the views
        searchBar = findViewById(R.id.search_bar);
        productLayout = findViewById(R.id.productLayout);

        productCard13 = findViewById(R.id.productCard13);
        productCard14 = findViewById(R.id.productCard14);
        productCard15 = findViewById(R.id.productCard15);
        productCard16 = findViewById(R.id.productCard16);

        product_name13 = findViewById(R.id.product_name13);
        product_name14 = findViewById(R.id.product_name14);
        product_name15 = findViewById(R.id.product_name15);
        product_name16 = findViewById(R.id.product_name16);

        // Setup button listeners for adding products to the cart
        setupProductButtons();

        // Add a TextWatcher to the search bar to filter products as the user types
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filter the products based on the search query
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Setup click listeners for product buttons to add items to the cart
    private void setupProductButtons() {
        Button product13Button = findViewById(R.id.product13);
        Button product14Button = findViewById(R.id.product14);
        Button product15Button = findViewById(R.id.product15);
        Button product16Button = findViewById(R.id.product16);

        // Define the action for each button
        product13Button.setOnClickListener(v -> addToCart("Bakers Dog Treats", 25));
        product14Button.setOnClickListener(v -> addToCart("Dental Chews", 20));
        product15Button.setOnClickListener(v -> addToCart("Beef Liver Treats", 35));
        product16Button.setOnClickListener(v -> addToCart("Salmon Bites", 15));
    }

    // Add a product to the cart using SharedPreferences
    private void addToCart(String name, int price) {
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String itemKey = name + "-";

        // Check if the item is already in the cart
        String existingItem = sharedPreferences.getString(itemKey, null);
        if (existingItem != null) {
            Toast.makeText(this, name + " is already in the cart", Toast.LENGTH_SHORT).show();
            return;
        }

        // Store the product name and price in SharedPreferences
        editor.putString(itemKey, price + "|");
        editor.apply();

        // Show a Toast message indicating that the item was added to the cart
        Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // Filter products based on the search query
    private void filterProducts(String query) {
        // Get the first letter of the query and convert it to uppercase
        String firstLetter = query.length() > 0 ? query.substring(0, 1).toUpperCase() : "";

        // Set the visibility of each product card based on whether the product name starts with the first letter of the query
        productCard13.setVisibility(product_name13.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard14.setVisibility(product_name14.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard15.setVisibility(product_name15.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard16.setVisibility(product_name16.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
    }
}
