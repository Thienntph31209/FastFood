package com.example.fastfood.List_Activity.Main_Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.fastfood.Adapter.Bill_Adapter;
import com.example.fastfood.Model.Bill;
import com.example.fastfood.R;
import com.example.fastfood.databinding.ActivityBillHistoryBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Bill_History extends AppCompatActivity {
    Bill_Adapter adapter;
    private ActivityBillHistoryBinding binding;
    private SharedPreferences shareRef;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_history);
        binding = ActivityBillHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shareRef = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        userId = shareRef.getString("userId","");
        binding.backBillHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Bill_History.this, MainActivity_User.class));
            }
        });
        

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        binding.rcvBillHistory.setLayoutManager(manager);
        FirebaseRecyclerOptions<Bill> options =
                new FirebaseRecyclerOptions.Builder<Bill>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Bill").orderByChild("user_Id").equalTo(userId), Bill.class)
                        .build();
        adapter = new Bill_Adapter(options);
        binding.rcvBillHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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