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

public class Kibblecategory extends AppCompatActivity {

    private LinearLayout productLayout;
    private EditText searchBar;

    private CardView productCard1, productCard2, productCard3, productCard4;
    private TextView product_name1, product_name2, product_name3, product_name4;

    // Called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in 'kibblecategory_activity.xml'
        setContentView(R.layout.kibblecategory_activity);

        // Initialize the search bar and product layout
        searchBar = findViewById(R.id.search_bar);
        productLayout = findViewById(R.id.productLayout);

        // Initialize the CardViews and TextViews for the products
        productCard1 = findViewById(R.id.productCard1);
        productCard2 = findViewById(R.id.productCard2);
        productCard3 = findViewById(R.id.productCard3);
        productCard4 = findViewById(R.id.productCard4);

        product_name1 = findViewById(R.id.product_name1);
        product_name2 = findViewById(R.id.productName2);
        product_name3 = findViewById(R.id.productName3);
        product_name4 = findViewById(R.id.productName4);

        // Setup buttons for adding products to the cart
        setupProductButtons();

        // Add a TextWatcher to filter products based on the search query
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filter products as user types in the search bar
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Initialize product buttons and set their click listeners
    private void setupProductButtons() {
        Button product1Button = findViewById(R.id.product1);
        Button product2Button = findViewById(R.id.product2);
        Button product3Button = findViewById(R.id.product3);
        Button product4Button = findViewById(R.id.product4);

        // Set up click listeners to add items to the cart with specific names and prices
        product1Button.setOnClickListener(v -> addToCart("Diamond Naturals", 50));
        product2Button.setOnClickListener(v -> addToCart("Blue Buffalo Wilderness High Protein", 40));
        product3Button.setOnClickListener(v -> addToCart("Wellness CORE Grain-Free Original", 60));
        product4Button.setOnClickListener(v -> addToCart("Royal Canin Size Health Nutrition", 50));
    }

    // Add an item to the cart using SharedPreferences
    private void addToCart(String name, int price) {
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String itemKey = name + "-";
        editor.putString(itemKey, price + "|");
        editor.apply();

        // Show a toast message to confirm that the item was added
        Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
    }

    // Filter visible products based on the search query
    private void filterProducts(String query) {
        String firstLetter = query.length() > 0 ? query.substring(0, 1).toUpperCase() : "";

        // Show or hide product cards based on whether their names start with the query's first letter
        productCard1.setVisibility(product_name1.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard2.setVisibility(product_name2.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard3.setVisibility(product_name3.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
        productCard4.setVisibility(product_name4.getText().toString().startsWith(firstLetter) ? View.VISIBLE : View.GONE);
    }
}
