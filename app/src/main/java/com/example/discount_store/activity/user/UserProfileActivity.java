package com.example.discount_store.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.discount_store.R;
import com.example.discount_store.activity.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextInputLayout fullName, email, phoneNo, password;
    TextView fullNameLabel, usernameLabel;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //ShowAllData
        showAllUserData();

        /*------------------Tool Bar--------------*/
        setSupportActionBar(toolbar);

        /*-----------------Navigation Drawer Menu ---------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phoneNo");
        _PASSWORD = intent.getStringExtra("password");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);
    }

    public void update(View view) {
        String text = "";
        if(isNameChanged()) {
            text += "name ";
        }
        if(isPasswordChanged() ) {
            text += "password ";
        }
        if (isPhoneNOChanged()) {
            text += "phone number ";
        }
        if (isEmailChanged()) {
            text += "email ";
        }

        if(!text.equals("")) {
            Toast.makeText(this, text + " has been updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data has not been updated", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPhoneNOChanged(){
        if(!_PHONENO.equals(phoneNo.getEditText().getText().toString()))
        {
            reference.child(_USERNAME).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            _PHONENO = phoneNo.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isEmailChanged(){
        if(!_EMAIL.equals(email.getEditText().getText().toString()))
        {
            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
            _EMAIL = email.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isPasswordChanged(){
        if(!_PASSWORD.equals(password.getEditText().getText().toString()))
        {
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged(){
        if(!_NAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            fullNameLabel.setText(_NAME);
            return true;
        }else{
            return false;
        }
    }

    public void delete(View view) {
        reference.child(_USERNAME).removeValue();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}