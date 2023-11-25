package com.example.fastfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastfood.Model.Product;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Product_Adapter extends FirebaseRecyclerAdapter<Product,Product_Adapter.ViewHolder>{
    private Context context;
    public Product_Adapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Product model) {
        holder.tv_name_product.setText(model.getName());
        holder.tv_describe_product.setText(model.getDescribe());
        holder.tv_pdt_product.setText(model.getProduct_Type_Id());
        holder.tv_id_product.setText(model.getId());
        holder.tv_price_product.setText(String.format("%d",model.getPrice())+"K");
        Glide.with(holder.img_product.getContext())
                .load(model.getImg_Product())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img_product);
        holder.btn_delete_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_id_product.getContext());
                builder.setMessage("Do you want to delete");

                builder.setPositiveButton("Yes", (dialog, which) -> {
                    // Thực hiện xóa sản phẩm khi người dùng xác nhận
                    Toast.makeText(holder.tv_id_product.getContext(), "Del succes", Toast.LENGTH_SHORT).show();

                    // Thay vì sử dụng getRef(position).getKey(), hãy sử dụng model.getId()
                    FirebaseDatabase.getInstance().getReference().child("Product")
                            .child(model.getId()).removeValue();
                });

                builder.setNegativeButton("No", (dialog, which) -> {
                    // Đóng AlertDialog nếu người dùng chọn hủy
                    Toast.makeText(holder.tv_id_product.getContext(), "Cancel",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_name_product, tv_describe_product, tv_price_product, tv_pdt_product, tv_id_product;
        ImageView img_product;
        Button btn_delete_product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_product = itemView.findViewById(R.id.name_item_product);
            tv_describe_product = itemView.findViewById(R.id.tv_describe_product);
            tv_price_product = itemView.findViewById(R.id.tv_price_product);
            tv_pdt_product = itemView.findViewById(R.id.tv_pdt_product);
            tv_id_product = itemView.findViewById(R.id.tv_id_product);
            img_product = itemView.findViewById(R.id.img_product);
            btn_delete_product = itemView.findViewById(R.id.btn_delete_product);
        }
    }

}
