package com.example.fastfood.List_Activity.Activity_Management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fastfood.Adapter.Product_Type_Adapter;
import com.example.fastfood.Model.ProductType;
import com.example.fastfood.R;
import com.example.fastfood.databinding.ActivityProductManagerBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Product_Type_Manager extends AppCompatActivity {
    Product_Type_Adapter adapter;
    RecyclerView rcv;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_type_manager);
        btn_back = findViewById(R.id.btn_back_product_type);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_Type_Manager.this, Product_Manager.class);
                startActivity(intent);
                finish();
            }
        });
        rcv = findViewById(R.id.rcv_product_type);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<ProductType> options = new FirebaseRecyclerOptions.Builder<ProductType>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Product_Type"), ProductType.class)
                .build();
        adapter = new Product_Type_Adapter(options);
        rcv.setAdapter(adapter);
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