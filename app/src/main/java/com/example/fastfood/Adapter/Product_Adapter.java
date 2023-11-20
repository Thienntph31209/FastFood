package com.example.fastfood.Adapter;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Product_Adapter extends RecyclerView.Adapter<Product_Adapter.ViewHolder>{
    Context context;
    ArrayList<Product> list;

    public Product_Adapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Product_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Product_Adapter.ViewHolder holder, int position) {
        Product product = list.get(position);
        holder.tv_name_product.setText(product.getTv_name_product());
        holder.tv_describe_product.setText(product.getTv_describe_product());
        holder.tv_price_product.setText(product.getTv_price_product());
        Picasso.get().load(product.getImg_product()).into(holder.img_product);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_name_product, tv_describe_product, tv_price_product;
        ImageView img_product;
        Button btn_add_product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_product = itemView.findViewById(R.id.tv_name_product);
            tv_describe_product = itemView.findViewById(R.id.tv_describe_product);
            tv_price_product = itemView.findViewById(R.id.tv_price_product);
            img_product = itemView.findViewById(R.id.img_product);
            btn_add_product = itemView.findViewById(R.id.btn_add_product);

        }
    }
}
