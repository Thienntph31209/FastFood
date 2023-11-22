package com.example.fastfood.List_Activity.Activity_Management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastfood.Adapter.Product_Adapter;
import com.example.fastfood.List_Activity.MainActivity_User;
import com.example.fastfood.Model.Product;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Product_Manager extends AppCompatActivity {
    ImageView btn_more_product, btn_back_product;
    RecyclerView rcv_product;
    Product_Adapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);

        btn_more_product = findViewById(R.id.btn_more_product);
        btn_back_product = findViewById(R.id.btn_back_product);
        btn_back_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_Manager.this, Product_Type_Manager.class);
                startActivity(intent);
                finish();
            }
        });
        rcv_product = findViewById(R.id.rcv_product);
        rcv_product.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"),Product.class)
                        .build();
        adapter = new Product_Adapter(options);
        rcv_product.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}