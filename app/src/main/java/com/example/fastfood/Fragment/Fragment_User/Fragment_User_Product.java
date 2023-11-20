package com.example.fastfood.Fragment.Fragment_User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fastfood.R;
public class Fragment_User_Product extends Fragment {
    ImageView btn_back_cartUser, btn_more_cartUser;
    TextView name_cart;
    RecyclerView rcv_product;
    public Fragment_User_Product() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__product, container, false);

        return v;
    }
}