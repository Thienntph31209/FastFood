package com.example.fastfood.Fragment.Fragment_User;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.fastfood.Adapter.Product_Adapter;
import com.example.fastfood.Adapter.Rcv1_adapter;
import com.example.fastfood.Adapter.Rcv2_adapter;
import com.example.fastfood.Adapter.Rcv3_adapter;
import com.example.fastfood.Model.Product;
import com.example.fastfood.Model.ProductType;
import com.example.fastfood.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Fragment_User_Main extends Fragment {
    private ViewFlipper viewFlipper;
    RecyclerView rcv1, rcv2, rcv3;
    Rcv1_adapter adapter;
    Rcv2_adapter adapter2;
    Rcv3_adapter adapter3;
    public Fragment_User_Main() {}
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment__main, container, false);
        //Slider
        viewFlipper = v.findViewById(R.id.slider);
            //img1
        ImageView img1 = new ImageView(getActivity());
        img1.setImageResource(R.drawable.img_slider_1);
        viewFlipper.addView(img1);
            //img2
        ImageView img2 = new ImageView(getActivity());
        img2.setImageResource(R.drawable.img_slider_2);
        viewFlipper.addView(img2);
            //img3
        ImageView img3 = new ImageView(getActivity());
        img3.setImageResource(R.drawable.img_slider_3);
        viewFlipper.addView(img3);
        //
        rcv1 = v.findViewById(R.id.rcv1_frm_main_user);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcv1.setLayoutManager(manager);
        FirebaseRecyclerOptions<ProductType> options =
                new FirebaseRecyclerOptions.Builder<ProductType>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product_Type"),ProductType.class)
                        .build();
        adapter = new Rcv1_adapter(options);
        rcv1.setAdapter(adapter);
        //
        rcv2 = v.findViewById(R.id.rcv2_frm_main_user);
        Log.d("rcv2", "RecyclerView found.");
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcv2.setLayoutManager(manager2);
        FirebaseRecyclerOptions<Product> options2 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"),Product.class)
                        .build();
        adapter2 = new Rcv2_adapter(options2);
        rcv2.setAdapter(adapter2);
        Log.d("rcv2", "Adapter set.");
        //
        rcv3 = v.findViewById(R.id.rcv3_frm_main_user);
        rcv3.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<Product> options3 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"),Product.class)
                        .build();
        adapter3 = new Rcv3_adapter(options3);
        rcv3.setAdapter(adapter3);
        return v;
        //
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewFlipper.startFlipping();
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        adapter2.startListening();
        adapter3.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter2.stopListening();
        adapter3.stopListening();
    }
}