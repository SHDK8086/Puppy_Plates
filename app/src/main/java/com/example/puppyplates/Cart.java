package com.example.puppyplates;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart extends AppCompatActivity implements CartAdapter.OnItemClickListener {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private Button checkoutButton;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        checkoutButton = findViewById(R.id.checkoutButton);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        // Setup RecyclerView with a linear layout manager and an adapter
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        List<CartItem> cartItems = getCartItems();
        cartAdapter = new CartAdapter(cartItems, this);
        recyclerViewCart.setAdapter(cartAdapter);

        // Update the total price of the items in the cart
        updateTotalPrice();

        // Handle checkout button click
        checkoutButton.setOnClickListener(v -> {
            double totalPrice = calculateTotalPrice(getCartItems());
            Intent intent = new Intent(Cart.this, Payment.class);
            intent.putExtra("TOTAL_PRICE", totalPrice);
            startActivity(intent);
        });
    }

    // Method triggered when an item is deleted from the cart
    @Override
    public void onItemDelete(int position) {
        List<CartItem> cartItems = getCartItems();
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
            saveCartItems(cartItems);
            cartAdapter.notifyItemRemoved(position);
            cartAdapter.notifyItemRangeChanged(position, cartItems.size());
            updateTotalPrice();
        } else {
            Log.e("Cart", "Invalid position for item removal: " + position);
        }
    }

    // Method triggered when the quantity of an item in the cart is changed
    @Override
    public void onItemQuantityChanged(int position, int newQuantity) {
        List<CartItem> cartItems = getCartItems();
        if (position >= 0 && position < cartItems.size()) {
            CartItem item = cartItems.get(position);
            item.setQuantity(newQuantity);
            saveCartItems(cartItems);
            cartAdapter.notifyItemChanged(position);
            updateTotalPrice();
        } else {
            Log.e("Cart", "Invalid position for item quantity change: " + position);
        }
    }

    // Retrieve the list of items in the cart from SharedPreferences
    private List<CartItem> getCartItems() {
        List<CartItem> items = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String[] value = entry.getValue().toString().split("\\|");
            String name = entry.getKey();
            double price = 0.0;
            int quantity = 1;

            try {
                price = Double.parseDouble(value[0]);
                if (value.length > 1) {
                    quantity = Integer.parseInt(value[1]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            items.add(new CartItem(name, price, quantity));
        }

        Log.d("Cart", "Cart items: " + items.toString());
        return items;
    }

    // Save the list of cart items to SharedPreferences
    private void saveCartItems(List<CartItem> cartItems) {
        SharedPreferences sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        for (CartItem item : cartItems) {
            editor.putString(item.getName(), item.getPrice() + "|" + item.getQuantity());
        }
        editor.apply();
    }

    // Update the total price displayed in the cart
    private void updateTotalPrice() {
        List<CartItem> cartItems = getCartItems();
        double totalPrice = calculateTotalPrice(cartItems);
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }

    // Calculate the total price of all items in the cart
    private double calculateTotalPrice(List<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
