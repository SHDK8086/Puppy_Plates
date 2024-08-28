package com.example.puppyplates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private OnItemClickListener onItemClickListener;

    // Constructor to initialize cart items and the click listener
    public CartAdapter(List<CartItem> cartItems, OnItemClickListener onItemClickListener) {
        this.cartItems = cartItems;
        this.onItemClickListener = onItemClickListener;
    }

    // Inflate the layout for individual cart items
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    // Bind data to each view holder (cart item)
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.name.setText(cartItem.getName());
        holder.price.setText(String.format("$%.2f", cartItem.getPrice()));
        holder.quantity.setText(String.valueOf(cartItem.getQuantity()));

        // Increase quantity button
        holder.quantity.setOnClickListener(v -> {
            int newQuantity = cartItem.getQuantity() + 1;
            cartItem.setQuantity(newQuantity);
            if (onItemClickListener != null) {
                onItemClickListener.onItemQuantityChanged(position, newQuantity);
            }
        });

        // Reduce quantity button
        holder.reduceQuantity.setOnClickListener(v -> {
            int newQuantity = cartItem.getQuantity() - 1;
            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                if (onItemClickListener != null) {
                    onItemClickListener.onItemQuantityChanged(position, newQuantity);
                }
            } else {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemDelete(position);
                }
            }
        });

        // Delete item button
        holder.delete.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemDelete(position);
                cartItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartItems.size());
            }
        });
    }

    // Return the number of items in the cart
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Interface to handle item click events (delete, quantity change)
    public interface OnItemClickListener {
        void onItemDelete(int position);
        void onItemQuantityChanged(int position, int newQuantity);
    }

    // ViewHolder class to hold the view references for each item
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        Button quantity;
        ImageView delete;
        Button reduceQuantity;

        // Constructor to bind views
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cartItemName);
            price = itemView.findViewById(R.id.cartItemPrice);
            quantity = itemView.findViewById(R.id.cartItemQuantity);
            delete = itemView.findViewById(R.id.cartItemDelete);
            reduceQuantity = itemView.findViewById(R.id.cartItemReduceQuantity);
        }
    }
}
