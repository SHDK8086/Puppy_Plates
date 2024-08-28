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

public class Supplimentcategory extends AppCompatActivity {

    private LinearLayout productLayout;
    private EditText searchBar;

    private CardView productCard9, productCard10, productCard11, productCard12;
    private TextView product_name9, product_name10, product_name11, product_name12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout file 'supplimentscategory_activity.xml'
        setContentView(R.layout.supplimentscategory_activity);

        // Initialize the views
        searchBar = findViewById(R.id.search_bar);
        productLayout = findViewById(R.id.productLayout);

        productCard9 = findViewById(R.id.productCard9);
        productCard10 = findViewById(R.id.productCard10);
        productCard11 = findViewById(R.id.productCard11);
        productCard12 = findViewById(R.id.productCard12);

        product_name9 = findViewById(R.id.product_name9);
        product_name10 = findViewById(R.id.product_name10);
        product_name11 = findViewById(R.id.product_name11);
        product_name12 = findViewById(R.id.product_name12);

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
        Button product9Button = findViewById(R.id.product9);
        Button product10Button = findViewById(R.id.product10);
        Button product11Button = findViewById(R.id.product11);
        Button product12Button = findViewById(R.id.product12);

        // Define the action for each button
        product9Button.setOnClickListener(v -> addToCart("Omega-3 Fish Oil", 20));
        product10Button.setOnClickListener(v -> addToCart("Probiotics", 60));
        product11Button.setOnClickListener(v -> addToCart("Vitamin E", 10));
        product12Button.setOnClickListener(v -> addToCart("Hemp Oil", 30));
    }

    // Add a product to the cart using SharedPreferences
    private void addToCart(String name, int price) {
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String itemKey = name;
        // Store the product name and price in SharedPreferences
        editor.putString(itemKey, String.valueOf(price));
        editor.apply();

        // Show a Toast message indicating that the item was added to the cart
        Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // Filter products based on the search query
    private void filterProducts(String query) {
        // Get the first letter of the query and convert it to uppercase
        String firstLetter = query.length() > 0 ? query.substring(0, 1).toUpperCase() : "";

        // Set the visibility of each product card based on whether the product name starts with the first letter of the query
        productCard9.setVisibility(product_name9.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard10.setVisibility(product_name10.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard11.setVisibility(product_name11.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard12.setVisibility(product_name12.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
    }
}
