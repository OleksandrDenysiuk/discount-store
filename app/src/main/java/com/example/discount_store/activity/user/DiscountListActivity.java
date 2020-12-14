package com.example.discount_store.activity.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.discount_store.R;

public class DiscountListActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;

    String[] countryNames = {"Australia", "Brazil", "China", "France", "Germany", "India", "Ireland", "Italy"
            , "Mexico", "Poland"};
    int[] countryFlags = {R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty,R.drawable.empty};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_list);

        mToolbar = findViewById(R.id.toolbar);
        //mToolbar.setTitle(getResources().getString(R.string.app_name));
        mListView = findViewById(R.id.listview);
        DiscountAdapter discountAdapter = new DiscountAdapter(DiscountListActivity.this, countryNames, countryFlags);
        mListView.setAdapter(discountAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(DiscountListActivity.this, DiscountDetailsActivity.class);
                mIntent.putExtra("countryName", countryNames[i]);
                mIntent.putExtra("countryFlag", countryFlags[i]);
                startActivity(mIntent);
            }
        });
    }
}