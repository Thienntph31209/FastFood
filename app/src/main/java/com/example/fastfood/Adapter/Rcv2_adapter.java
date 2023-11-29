package com.example.fastfood.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.Model.Cart;
import com.example.fastfood.Model.Product;
import com.example.fastfood.Model.User;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Rcv2_adapter extends FirebaseRecyclerAdapter<Product,Rcv2_adapter.ViewHolder> {
    private Context context;
    private String username;

    public void setUsername(String username){
        this.username = username;
    }

    public Rcv2_adapter(@NonNull FirebaseRecyclerOptions<Product> options, Context context) {
        super(options);
        this.context = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Product model) {
        Log.d("rcv2", "Binding data for position: " + position + ", Product: " + model.toString());
        holder.tv_rcv2_id.setText(model.getId());
        holder.tv_rcv2_describe.setText(model.getDescribe());
        holder.tv_rcv2_price.setText(String.valueOf(model.getPrice()+"K"));
        holder.tv_rcv2_typeId.setText(model.getProduct_Type_Id());
        holder.tv_rcv2_name.setText(model.getName());

        Glide.with(holder.img_rcv2.getContext())
                .load(model.getImg_Product())
                .into(holder.img_rcv2);

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện click vào item_fab_rcv2 ở đây
                    addToCart(model); // Gửi ID của người dùng khi thêm sản phẩm vào giỏ hàng

            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv2_main_user,parent,false);
        Log.d("rcv2", "onCreateViewHolder called");
        return new ViewHolder(v);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_rcv2_name, tv_rcv2_price, tv_rcv2_id, tv_rcv2_describe, tv_rcv2_typeId;
        ImageView img_rcv2, addtocart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rcv2_name = itemView.findViewById(R.id.item_name_rcv2);
            img_rcv2 = itemView.findViewById(R.id.item_img_rcv2);
            tv_rcv2_price = itemView.findViewById(R.id.item_price_rcv2);
            tv_rcv2_id = itemView.findViewById(R.id.item_id_rcv2);
            tv_rcv2_describe = itemView.findViewById(R.id.item_des_rcv2);
            tv_rcv2_typeId = itemView.findViewById(R.id.item_type_rcv2);
            addtocart = itemView.findViewById(R.id.addtocart);
        }
    }

    private void addToCart(Product product){
        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("Cart").push();
        String cartId = UUID.randomUUID().toString();

        Cart cartItem = new Cart();

        cartItem.setCart_Id(cartId);
        cartItem.setId(product.getId());
        cartItem.setCart_quantity(1);
        cartRef.setValue(cartItem)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thêm sản phẩm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("addToCart", "Thêm sản phẩm vào giỏ hàng không thành công: " + e.getMessage());
                        Toast.makeText(context, "Thêm sản phẩm vào giỏ hàng không thành công", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
