package com.example.fastfood.Fragment.Fragment_User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastfood.R;
public class Fragment_User_Personal_Information extends Fragment {
    public Fragment_User_Personal_Information() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__personal__information, container, false);
    }
}