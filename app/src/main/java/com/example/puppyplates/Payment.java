package com.example.puppyplates;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout file 'payment_activity.xml'
        setContentView(R.layout.payment_activity);

        // Retrieve the total price from the intent that started this activity
        double totalPrice = getIntent().getDoubleExtra("TOTAL_PRICE", 0.0);

        // Find the TextView for displaying the total price
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        // Set the text of the TextView to show the total price formatted as currency
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));

        // Find the MaterialButton for the payment action
        MaterialButton paybtn = findViewById(R.id.paybtn);
        // Set an OnClickListener for the payment button
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a Toast message when the payment button is clicked
                Toast.makeText(Payment.this, "Payment Successful, Your Order Has Been Placed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
