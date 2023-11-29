package com.example.fastfood.Fragment.Fragment_Login_Account;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fastfood.Fragment.Fragment_User.Fragment_User_Main;
import com.example.fastfood.List_Activity.Activity_Login.Forget_Password;
import com.example.fastfood.List_Activity.Main_Activity.MainActivity_Admin;
import com.example.fastfood.List_Activity.Main_Activity.MainActivity_User;
import com.example.fastfood.Model.User;
import com.example.fastfood.R;
import com.example.fastfood.databinding.FragmentLoginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Fragment_Login extends Fragment {
    public Fragment_Login() {
    }
    private FragmentLoginBinding binding;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        // đăng nhập gmail
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        binding.gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIntent = mGoogleSignInClient.getSignInIntent();
                registerForActivityResult.launch(signIntent);
            }
        });

        // đăng nhập facebook
        CallbackManager callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserProfile(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });

        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(Fragment_Login.this,Arrays.asList("public_profile"));
            }
        });

        // đăng nhập ở màn chính
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("User");
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.edLoginUserName.getText().toString().trim();
                String password = binding.edLoginPassWord.getText().toString().trim();

                if (userName.isEmpty() || password.isEmpty()) {
                    if (userName.isEmpty()) {
                        binding.inLoginUserName.setError("Vui lòng nhập tên đăng nhập");
                    }
                    if (password.isEmpty()) {
                        binding.inLoginPassWord.setError("Vui lòng nhập mật khẩu");
                    }
                }else{
                    databaseRef.orderByChild("user_Name").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                                    User user = snapshot1.getValue(User.class);
                                    if(user != null && user.getPass_Word().equals(password)){
                                        saveLoginStatus(true, userName, password, user.getRole());
                                        if(user.getRole() == 0){
                                            startActivity(new Intent(getActivity(), MainActivity_Admin.class));
                                        }else if(user.getRole() == 1){
                                            startActivity(new Intent(getActivity(), MainActivity_User.class));
                                        }
                                    }else{
                                        binding.inLoginPassWord.setError("Mật khẩu sai vui lòng nhập lại mật khẩu!");
                                    }
                                }
                            }else{
                                binding.inLoginUserName.setError("Tài khoản không tồn tại!");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        // Quên mật khẩu
        binding.ForgetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Forget_Password.class));
            }
        });
        return view;
    }

    // thực hiện chức năng đăng nhập một lần
    private void saveLoginStatus(boolean isLoggedIN, String username, String password, int role){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("loginStatus", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIN);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("role", String.valueOf(role));
        editor.apply();

    }

    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("loginStatus", Activity.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        String roleString = sharedPreferences.getString("role", "");

        int userRole = -1; // Đặt giá trị mặc định là -1

        if (!roleString.isEmpty()) {
            try {
                userRole = Integer.parseInt(roleString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Xử lý khi chuỗi không thể chuyển đổi thành số nguyên
            }
        }

        if (isLoggedIn) {
            switch (userRole) {
                case 0: // Người dùng thông thường
                    startActivity(new Intent(getActivity(), MainActivity_Admin.class));
                    break;
                case 1: // Quản trị viên
                    startActivity(new Intent(getActivity(), MainActivity_User.class));
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        checkLoginStatus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // giải phóng binding khi Fragment hủy
        binding = null;
    }

    // đăng nhập bằng gmail
    ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == Activity.RESULT_OK){
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    String email = account.getEmail();
                    String name = account.getDisplayName();
                    Toast.makeText(getContext(), "email" + email + "\n" + "name" + name, Toast.LENGTH_SHORT).show();

                    // Chuyển đến màn hình chính sau khi đăng nhập thành công
                    Intent intent = new Intent(getActivity(), MainActivity_User.class);
                    startActivity(intent);
                    getActivity().finish(); // Đóng Fragment_Login nếu bạn muốn

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }
    });
    // đăng nhập bằng facebook
    private void getUserProfile(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                try {
                    String name = jsonObject.getString("Name");
                    String email = jsonObject.getString("id");
                    String image = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");
                    Toast.makeText(getContext(), name + " - " + email + " - " + image, Toast.LENGTH_SHORT).show();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        request.executeAsync();
    }
}