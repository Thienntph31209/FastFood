package com.example.fastfood.List_Activity.Activity_Management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfood.Adapter.Product_Adapter;
import com.example.fastfood.List_Activity.Main_Activity.MainActivity_Admin;
import com.example.fastfood.Model.Product;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product_Manager extends AppCompatActivity {
    Context context;
    ImageView btn_next_product, btn_back_product;
    RecyclerView rcv_product;
    Product_Adapter adapter;
    FloatingActionButton fab;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);

        btn_next_product = findViewById(R.id.btn_next_product);
        btn_back_product = findViewById(R.id.btn_back_product);
        btn_back_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_Manager.this, MainActivity_Admin.class);
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
        btn_next_product = findViewById(R.id.btn_next_product);
        btn_next_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_Manager.this, Product_Type_Manager.class);
                startActivity(intent);
                finish();
            }
        });
        rcv_product.setAdapter(adapter);
        fab = findViewById(R.id.fab_add_product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog_Add();
            }
        });
        rcv_product.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        //

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
    public void showDialog_Add(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_product);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.style_dialog);
        TextInputEditText id, name, img, describe, price, type_id;
        Button btn_add;
        id = dialog.findViewById(R.id.dialog_id_product);
        name = dialog.findViewById(R.id.dialog_name_product);
        describe = dialog.findViewById(R.id.dialog_describe_product);
        price = dialog.findViewById(R.id.dialog_price_product);
        img = dialog.findViewById(R.id.dialog_img_product);
        type_id = dialog.findViewById(R.id.dialog_typeId_product);
        btn_add = dialog.findViewById(R.id.dialog_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data_id = id.getText().toString().trim();
                String data_name = name.getText().toString().trim();
                String data_describe = describe.getText().toString().trim();
                int data_price = Integer.parseInt(price.getText().toString().trim());
                String data_img = img.getText().toString().trim();
                String data_typeId = type_id.getText().toString().trim();
                //Product product = new Product(data_id, data_name, data_describe, data_typeId, data_img, data_price);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Product");
                String key = databaseReference.push().getKey();
                //
                databaseReference.child(key).child("Id").setValue(data_id);
                databaseReference.child(key).child("Describe").setValue(data_describe);
                databaseReference.child(key).child("Img_Product").setValue(data_img);
                databaseReference.child(key).child("Name").setValue(data_name);
                databaseReference.child(key).child("Price").setValue(data_price);
                databaseReference.child(key).child("Product_Type_Id").setValue(data_typeId);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void showDialog_Update(Product product){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_product);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.style_dialog);
        TextInputEditText id, name, img, describe, price, type_id;
        Button btn_update;
        id = dialog.findViewById(R.id.dialog_id_product_up);
        name = dialog.findViewById(R.id.dialog_name_product_up);
        describe = dialog.findViewById(R.id.dialog_describe_product_up);
        price = dialog.findViewById(R.id.dialog_price_product_up);
        img = dialog.findViewById(R.id.dialog_img_product_up);
        type_id = dialog.findViewById(R.id.dialog_typeId_product_up);
        btn_update = dialog.findViewById(R.id.dialog_btn_update);
        //get dữ liệu của item lên dialog
        id.setText(product.getId());
        name.setText(product.getName());
        describe.setText(product.getDescribe());
        price.setText(product.getPrice());
        img.setText(product.getImg_Product());
        type_id.setText(product.getProduct_Type_Id());
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idd = id.getText().toString();
                String namee = name.getText().toString();
                String describee = describe.getText().toString();
                int pricee = Integer.parseInt(price.getText().toString());
                String imgg = img.getText().toString();
                String type_idd = type_id.getText().toString();
                //Lưu
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Product");
                databaseReference.child(product.getId()).child("Describe").setValue(describee);
                databaseReference.child(product.getId()).child("Img_Product").setValue(imgg);
                databaseReference.child(product.getId()).child("Name").setValue(namee);
                databaseReference.child(product.getId()).child("Price").setValue(pricee);
                databaseReference.child(product.getId()).child("Product_Type_Id").setValue(type_idd);

                dialog.dismiss();
            }
        });
        dialog.show();
    }
}