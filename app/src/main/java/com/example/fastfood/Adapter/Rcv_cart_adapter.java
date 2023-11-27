package com.example.fastfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.Model.Product;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Rcv_cart_adapter extends FirebaseRecyclerAdapter<Product,Rcv_cart_adapter.ViewHolder> {
    public Rcv_cart_adapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Product model) {
        holder.tv_rcvCart_id.setText(model.getId());
        holder.tv_rcvCart_describe.setText(model.getDescribe());
        holder.tv_rcvCart_price.setText(model.getPrice()+"K");
        holder.tv_rcvCart_typeId.setText(model.getProduct_Type_Id());
        holder.tv_rcvCart_name.setText(model.getName());
        Glide.with(holder.img_rcvCart.getContext())
                .load(model.getImg_Product())
                .into(holder.img_rcvCart);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_user,parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_rcvCart_name, tv_rcvCart_price, tv_rcvCart_id, tv_rcvCart_describe, tv_rcvCart_typeId;
        ImageView img_rcvCart;
        Button btn_del_rcvCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rcvCart_name = itemView.findViewById(R.id.name_item_cart_user);
            img_rcvCart = itemView.findViewById(R.id.img_cart_user);
            tv_rcvCart_price = itemView.findViewById(R.id.tv_price_cart_user);
            btn_del_rcvCart = itemView.findViewById(R.id.btn_delete_cart_user);
            tv_rcvCart_id = itemView.findViewById(R.id.tv_id_cart_user);
            tv_rcvCart_describe = itemView.findViewById(R.id.tv_describe_cart_user);
            tv_rcvCart_id = itemView.findViewById(R.id.tv_pdt_cart_user);
        }
    }
}
