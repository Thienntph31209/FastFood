package com.example.fastfood.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.HashMap;
import java.util.Map;

public class Rcv2_adapter extends FirebaseRecyclerAdapter<Product,Rcv2_adapter.ViewHolder> {

    public Rcv2_adapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Product model) {
        Log.d("rcv2", "Binding data for position: " + position + ", Product: " + model.toString());
        holder.tv_rcv2_id.setText(model.getId());
        holder.tv_rcv2_describe.setText(model.getDescribe());
        holder.tv_rcv2_price.setText(model.getPrice()+"K");
        holder.tv_rcv2_typeId.setText(model.getProduct_Type_Id());
        holder.tv_rcv2_name.setText(model.getName());
        Glide.with(holder.img_rcv2.getContext())
                .load(model.getImg_Product())
                .into(holder.img_rcv2);
        holder.btn_add_rcv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        ImageView img_rcv2;
        FloatingActionButton btn_add_rcv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rcv2_name = itemView.findViewById(R.id.item_name_rcv2);
            img_rcv2 = itemView.findViewById(R.id.item_img_rcv2);
            tv_rcv2_price = itemView.findViewById(R.id.item_price_rcv2);
            btn_add_rcv2 = itemView.findViewById(R.id.item_fab_rcv2);
            tv_rcv2_id = itemView.findViewById(R.id.item_id_rcv2);
            tv_rcv2_describe = itemView.findViewById(R.id.item_des_rcv2);
            tv_rcv2_typeId = itemView.findViewById(R.id.item_type_rcv2);
        }
    }
}
