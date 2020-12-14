package com.example.discount_store.activity.user;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.discount_store.R;

public class DiscountDetailsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mImageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_details);

        mToolbar = findViewById(R.id.toolbar2);
        mImageView = findViewById(R.id.imageView2);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mToolbar.setTitle(mBundle.getString("countryName"));
            mImageView.setImageResource(mBundle.getInt("countryFlag"));
        }
    }
}