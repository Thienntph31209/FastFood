package com.example.fastfood.List_Activity.Main_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fastfood.R;
import com.example.fastfood.databinding.ActivityConfirmedOrderBinding;

public class Confirmed_order extends AppCompatActivity {
    private ActivityConfirmedOrderBinding binding;
    String bill_Id,fullName,purchase_Date;
    int totalCost,phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_order);
        binding = ActivityConfirmedOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if(intent != null){
            bill_Id = intent.getStringExtra("bill_Id");
            fullName = intent.getStringExtra("fullName");
            phoneNumber = intent.getIntExtra("phoneNumber",0);
            purchase_Date = intent.getStringExtra("purchase_Date");
            totalCost = intent.getIntExtra("total_Cost",0);
        }

        binding.confirmOrderId.setText(bill_Id);
        binding.confirmTotal.setText(String.valueOf(totalCost) + " VNĐ");
        binding.confirmDateTime.setText(purchase_Date);
        binding.confirmName.setText(fullName);
        binding.confirmPhone.setText(String.valueOf("0" + phoneNumber));

        binding.backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Confirmed_order.this, MainActivity_User.class));
            }
        });
    }
}