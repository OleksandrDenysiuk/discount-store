package com.example.discount_store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.discount_store.R;
import com.example.discount_store.activity.shop.ShopDashBoardActivity;
import com.example.discount_store.activity.user.DiscountListActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button callSignUp, login_btn;
    TextInputLayout username, password;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void loginUser() {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
        } else {
            isUser();
        }
    }


    public void loginShop(){
        if (!validateUsername() | !validatePassword()) {
        } else {
            isShop();
        }
    }

    private void isUser() {
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        Intent intent = new Intent(getApplicationContext(), DiscountListActivity.class);
                        /*intent.putExtra("name", snapshot.child(userEnteredUsername).child("name").getValue(String.class));
                        intent.putExtra("username", snapshot.child(userEnteredUsername).child("username").getValue(String.class));
                        intent.putExtra("email", snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class));
                        intent.putExtra("phoneNo", snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class));
                        intent.putExtra("password", passwordFromDB);*/
                        startActivity(intent);
                    } else {
                        //progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    //progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void isShop(){
        final String shopEnteredUsername = username.getEditText().getText().toString().trim();
        final String shopEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("shops");
        Query checkShop = reference.orderByChild("login").equalTo(shopEnteredUsername);
        checkShop.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(shopEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(shopEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        Intent intent = new Intent(getApplicationContext(), ShopDashBoardActivity.class);
                        startActivity(intent);
                    } else {
                        //progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    //progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_login_field);
        password = findViewById(R.id.password_login_field);
        radioGroup = findViewById(R.id.myRadioGroup);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if(radioButton.getText().equals("AS USER")){
                    loginUser();
                } else {
                    loginShop();
                }
            }
        });

        callSignUp = findViewById(R.id.register_btn);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }
}