package com.example.fastfood.Fragment.Fragment_User;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastfood.Adapter.Rcv2_adapter;
import com.example.fastfood.Adapter.Rcv_cart_adapter;
import com.example.fastfood.Model.Product;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Fragment_User_Product extends Fragment{
    Rcv_cart_adapter adapter;
    RecyclerView rcv_cart_user;
    Button btn_order;
    public Fragment_User_Product() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__product, container, false);
        btn_order = v.findViewById(R.id.btn_order_user);
        rcv_cart_user = v.findViewById(R.id.rcv_cart_user);
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), Product.class)
                        .build();
        adapter = new Rcv_cart_adapter(options);

        rcv_cart_user.setAdapter(adapter);

        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}