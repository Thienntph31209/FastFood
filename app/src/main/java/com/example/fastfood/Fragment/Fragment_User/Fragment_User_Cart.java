package com.example.fastfood.Fragment.Fragment_User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastfood.Adapter.Rcv2_adapter;
import com.example.fastfood.databinding.FragmentProductBinding;

public class Fragment_User_Cart extends Fragment{
    public Fragment_User_Cart() {

    }

    private FragmentProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        View v = binding.getRoot();


        return v;
    }

}