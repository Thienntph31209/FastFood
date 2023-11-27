package com.example.fastfood.List_Activity.Main_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fastfood.Fragment.Fragment_Admin.Fragment_Admin_Personal_Information;
import com.example.fastfood.R;
import com.example.fastfood.databinding.ActivityChangepasswordBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Changepassword_Activity extends AppCompatActivity {
    private ActivityChangepasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        binding = ActivityChangepasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpassword = binding.edOldPassWord.getText().toString();
                String newpassword = binding.edNewPassWord.getText().toString();
                String repassword = binding.edRePassWord.getText().toString();

                if(oldpassword.isEmpty() || newpassword.isEmpty() || repassword.isEmpty()){
                    if(oldpassword.equals("")){
                        binding.inOldPassWord.setError("Vui lòng không để trống oldpass");
                    }else{
                        binding.inOldPassWord.setError(null);
                    }

                    if(newpassword.equals("")){
                        binding.inNewPassWord.setError("Vui lòng không để trống newpass");
                    }else{
                        binding.inNewPassWord.setError(null);
                    }

                    if(repassword.equals("")){
                        binding.inRePassWord.setError("Vui lòng không để trống newpass");
                    }else{
                        binding.inRePassWord.setError(null);
                    }
                }else{
                    changePassword(oldpassword, newpassword, repassword);
                }

            }
        });
    }

    private void changePassword(String oldPassword, String newPassword, String rePassword){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("User");

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String currentPassword = snapshot.child("pass_Word").getValue(String.class);

                    if(currentPassword != null && currentPassword.equals(oldPassword)){
                        if(newPassword.equals(rePassword)){
                            userRef.child("pass_Word").setValue(newPassword)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            startActivity(new Intent(Changepassword_Activity.this, Fragment_Admin_Personal_Information.class));
                                            Toast.makeText(Changepassword_Activity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Changepassword_Activity.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }else{
                            binding.inRePassWord.setError("Mật khẩu và nhập lại mật khẩu không khớp!");
                        }
                    }else{
                        binding.inOldPassWord.setError("Mật khẩu không khớp với mật khẩu cũ");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi truy vấn bị hủy
            }
        });
    }


}