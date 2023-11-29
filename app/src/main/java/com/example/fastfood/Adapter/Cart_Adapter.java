package com.example.fastfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.Model.Cart;
import com.example.fastfood.Model.Product;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cart_Adapter extends FirebaseRecyclerAdapter<Cart, Cart_Adapter.myViewHolder> {
    public Cart_Adapter(@NonNull FirebaseRecyclerOptions<Cart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Cart model) {
        // lấy thông tin sản phẩm từ Product_Id
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Product").child(model.getId());
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Product product = snapshot.getValue(Product.class);
                    holder.name_cart.setText(product.getName());
                    holder.price_cart.setText(product.getPrice());
                    Glide.with(holder.Img_Cart.getContext())
                            .load(product.getImg_Product())
                            .into(holder.Img_Cart);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.quantity_cart.setText(String.valueOf(model.getCart_quantity()));
        holder.add_cart_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedQuantity = model.getCart_quantity() + 1;
                DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(model.getCart_Id());
                cartRef.child("cart_quantity").setValue(updatedQuantity);
            }
        });
        holder.minus_cart_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedQuantity = model.getCart_quantity() - 1;
                if(updatedQuantity >= 0){
                    DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child(model.getCart_Id());
                    cartRef.child("cart_quantity").setValue(updatedQuantity);
                }
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addtocart,parent,false);
        return new myViewHolder(view);
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView Img_Cart, minus_cart_quantity, add_cart_quantity;
        TextView name_cart, price_cart, quantity_cart;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Img_Cart = itemView.findViewById(R.id.Img_Cart);
            minus_cart_quantity = itemView.findViewById(R.id.minus_cart_quantity);
            add_cart_quantity = itemView.findViewById(R.id.add_cart_quantity);
            name_cart = itemView.findViewById(R.id.name_cart);
            price_cart = itemView.findViewById(R.id.price_cart);
            quantity_cart = itemView.findViewById(R.id.quantity_cart);
        }
    }

}
