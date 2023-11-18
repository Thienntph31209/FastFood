package com.example.fastfood.Fragment.Fragment_Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastfood.R;

public class Fragment_Admin_Chart extends Fragment {
    public Fragment_Admin_Chart() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__admin__chart, container, false);
    }
}