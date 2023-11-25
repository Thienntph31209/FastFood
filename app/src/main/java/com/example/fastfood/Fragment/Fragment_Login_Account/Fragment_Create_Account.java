package com.example.fastfood.Fragment.Fragment_Login_Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fastfood.R;
import com.example.fastfood.databinding.FragmentCreateAccountBinding;

public class Fragment_Create_Account extends Fragment {
    public Fragment_Create_Account() {
    }

    private FragmentCreateAccountBinding binding;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        animation_login();

        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        // Giải phóng binding khi Fragment bị hủy
        binding = null;
    }

    private void animation_login(){
        binding.inCreateUserName.setTranslationX(800);
        binding.inCreatePassWord.setTranslationX(800);
        binding.inCreateRePassWord.setTranslationX(800);
        binding.btnLogin.setTranslationX(800);
        binding.footer.setTranslationY(300);

        binding.inCreateUserName.setAlpha(v);
        binding.inCreatePassWord.setAlpha(v);
        binding.inCreateRePassWord.setAlpha(v);
        binding.btnLogin.setAlpha(v);
        binding.footer.setAlpha(v);

        binding.inCreateUserName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.inCreatePassWord.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.inCreateRePassWord.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.btnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        binding.footer.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
    }
}