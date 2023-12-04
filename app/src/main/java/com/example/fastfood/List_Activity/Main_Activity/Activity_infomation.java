package com.example.fastfood.List_Activity.Main_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.fastfood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.fastfood.databinding.ActivityInfomationBinding;

public class Activity_infomation extends AppCompatActivity {
    private ActivityInfomationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        binding = ActivityInfomationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBackChangeInfoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity_User.class));
            }
        });
        binding.btnEditChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activity_changeInfo.class));
            }
        });
        String savedUsername = getUsernameFromSharedPreferences();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User");
        userRef.orderByChild("user_Name").equalTo(savedUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String userId = snapshot1.getKey();
                        String name = snapshot1.child("full_Name").getValue(String.class);
                        binding.tvFullNameChangeInfo.setText("Full name:   "+name);
                        String userName = snapshot1.child("user_Name").getValue(String.class);
                        binding.tvUserNameChangeInfo.setText("User name:   "+ userName);
                        String email = snapshot1.child("email").getValue(String.class);
                        binding.tvEmailChangeInfo.setText("Email:   "+ email);
                        String phoneNumber = String.valueOf(snapshot1.child("phone_Number").getValue(Integer.class));
                        binding.tvPhoneChangeInfo.setText("Phone number:   "+ "+84 "+ phoneNumber);
                        String address = snapshot1.child("address").getValue(String.class);
                        binding.tvAddressChangeInfo.setText("Address:   "+ address);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private String getUsernameFromSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("loginStatus", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("username", ""); // "" là giá trị mặc định nếu không tìm thấy username trong SharedPreferences
    }
}