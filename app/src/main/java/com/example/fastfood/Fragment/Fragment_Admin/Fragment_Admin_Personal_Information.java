package com.example.fastfood.Fragment.Fragment_Admin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastfood.List_Activity.Activity_Login.Main_Login;
import com.example.fastfood.List_Activity.Main_Activity.Changepassword_Activity;
import com.example.fastfood.R;
import com.example.fastfood.databinding.FragmentAdminPersonalInformationBinding;

public class Fragment_Admin_Personal_Information extends Fragment {
    public Fragment_Admin_Personal_Information() {

    }

    private FragmentAdminPersonalInformationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminPersonalInformationBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.LogoutAdminInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        binding.ChangepasswordAdminInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Changepassword_Activity.class));
            }
        });
        return view;
    }

    private void logout(){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("loginStatus", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(getActivity(), Main_Login.class));
    }
}