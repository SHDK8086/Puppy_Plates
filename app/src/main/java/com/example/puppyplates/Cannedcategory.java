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

public class Cannedcategory extends AppCompatActivity {

    // Declare UI elements
    private LinearLayout productLayout;
    private EditText searchBar;

    private CardView productCard5, productCard6, productCard7, productCard8;
    private TextView product_name5, product_name6, product_name7, product_name8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cannedcategory_activity);

        // Initialize UI elements
        searchBar = findViewById(R.id.search_bar);
        productLayout = findViewById(R.id.productLayout);

        productCard5 = findViewById(R.id.productCard5);
        productCard6 = findViewById(R.id.productCard6);
        productCard7 = findViewById(R.id.productCard7);
        productCard8 = findViewById(R.id.productCard8);

        product_name5 = findViewById(R.id.product_name5);
        product_name6 = findViewById(R.id.product_name6);
        product_name7 = findViewById(R.id.product_name7);
        product_name8 = findViewById(R.id.product_name8);

        // Setup buttons for adding products to the cart
        setupProductButtons();

        // Add a text change listener to the search bar to filter products
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Setup click listeners for product buttons
    private void setupProductButtons() {
        Button product5Button = findViewById(R.id.product5);
        Button product6Button = findViewById(R.id.product6);
        Button product7Button = findViewById(R.id.product7);
        Button product8Button = findViewById(R.id.product8);

        product5Button.setOnClickListener(v -> addToCart("Hill's Science Diet Adult Beef and Barley", 120));
        product6Button.setOnClickListener(v -> addToCart("Primal Pork Formula", 90));
        product7Button.setOnClickListener(v -> addToCart("Wellness CORE Grain-Free Original", 60));
        product8Button.setOnClickListener(v -> addToCart("Merrick Grain-Free Texas Beef and Sweet Potato", 50));
    }

    // Method to add a product to the cart
    private void addToCart(String name, int price) {
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String itemKey = name + "-";
        editor.putString(itemKey, price + "|1");  // Add default quantity "1" to the cart item
        editor.apply();

        Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // Method to filter products based on the search query
    private void filterProducts(String query) {
        String firstLetter = query.length() > 0 ? query.substring(0, 1).toUpperCase() : "";

        productCard5.setVisibility(product_name5.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard6.setVisibility(product_name6.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard7.setVisibility(product_name7.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard8.setVisibility(product_name8.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
    }
}
