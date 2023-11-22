package com.example.fastfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.fastfood.Model.ProductType;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Product_Type_Adapter extends FirebaseRecyclerAdapter<ProductType, Product_Type_Adapter.ViewHolder> {
    public Product_Type_Adapter(@NonNull FirebaseRecyclerOptions<ProductType> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductType model) {
        holder.tv_id_item_product_type.setText(model.getProduct_Type_Id());
        holder.tv_name_item_product_type.setText(model.getType_Name());
        Glide.with(holder.img_product_type.getContext())
                .load(model.getImg_Product_Type())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img_product_type);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type,parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_id_item_product_type, tv_name_item_product_type;
        ImageView img_product_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product_type = itemView.findViewById(R.id.img_product_type);
            tv_id_item_product_type = itemView.findViewById(R.id.tv_id_product_type);
            tv_name_item_product_type = itemView.findViewById(R.id.tv_name_product_type);
        }
    }
}
