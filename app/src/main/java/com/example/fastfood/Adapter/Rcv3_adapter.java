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


public class Rcv3_adapter extends FirebaseRecyclerAdapter<Product,Rcv3_adapter.ViewHolder> {
    public Rcv3_adapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Product model) {
        holder.tv_rcv3_id.setText(model.getId());
        holder.tv_rcv3_describe.setText(model.getDescribe());
        holder.tv_rcv3_price.setText(model.getPrice()+"K");
        holder.tv_rcv3_typeId.setText(model.getProduct_Type_Id());
        holder.tv_rcv3_name.setText(model.getName());
        Glide.with(holder.img_rcv3.getContext())
                .load(model.getImg_Product())
                .into(holder.img_rcv3);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv3_main_user,parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_rcv3_name, tv_rcv3_price, tv_rcv3_id, tv_rcv3_describe, tv_rcv3_typeId;
        ImageView img_rcv3;
        Button btn_add_rcv3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rcv3_name = itemView.findViewById(R.id.item_name_rcv3);
            img_rcv3 = itemView.findViewById(R.id.item_img_rcv3);
            tv_rcv3_price = itemView.findViewById(R.id.item_price_rcv3);
            btn_add_rcv3 = itemView.findViewById(R.id.item_btnAdd_rcv3);
            tv_rcv3_id = itemView.findViewById(R.id.item_id_rcv3);
            tv_rcv3_describe = itemView.findViewById(R.id.item_des_rcv3);
            tv_rcv3_typeId = itemView.findViewById(R.id.item_type_rcv3);
        }
    }
}
