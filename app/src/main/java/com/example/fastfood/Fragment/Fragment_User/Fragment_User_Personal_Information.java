package com.example.fastfood.Fragment.Fragment_User;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fastfood.List_Activity.Activity_Login.Main_Login;
import com.example.fastfood.R;
public class Fragment_User_Personal_Information extends Fragment {
    CardView btn_logout_user;
    public Fragment_User_Personal_Information() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__personal__information, container, false);
        btn_logout_user = v.findViewById(R.id.btn_logOut_user);
        btn_logout_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Logout", Toast.LENGTH_SHORT).show();
                logout();
            }
        });
        return v;
    }
    private void logout(){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("loginStatus", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(getActivity(), Main_Login.class));
    }
}