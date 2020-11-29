package com.example.discount_store.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discount_store.R;

public class ShopDashBoardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_dash_board);
    }

    public void createProduct(View view){
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        startActivity(intent);
    }

    public void createDiscount(View view) {
        Intent intent = new Intent(getApplicationContext(), DiscountActivity.class);
        startActivity(intent);
    }
}