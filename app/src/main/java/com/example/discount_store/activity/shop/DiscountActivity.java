package com.example.discount_store.activity.shop;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.discount_store.R;
import com.example.discount_store.model.Discount;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiscountActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    private static final String TAG = "DiscountActivity";
    List<String> productList;

    TextInputLayout name, rate;
    private TextView discount_start;
    private TextView discount_end;
    private Spinner productSpinner;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        discount_start = (TextView) findViewById(R.id.discount_start);
        discount_end = (TextView) findViewById(R.id.discount_end);
        name = findViewById(R.id.discount_name);
        rate = findViewById(R.id.discount_rate);
        productSpinner = findViewById(R.id.product_list);
        productList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    String productName = child.child("name").getValue(String.class);
                    productList.add(productName);
                }
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(DiscountActivity.this, android.R.layout.simple_spinner_item, productList);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                productSpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        discount_start.setOnClickListener(view -> createAndShowDatePickerDialog(discount_start));

        discount_end.setOnClickListener(view -> createAndShowDatePickerDialog(discount_end));
    }

    private void createAndShowDatePickerDialog(TextView discount_time_interval){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener = (datePicker, year1, month1, day1) -> {
            month1 = month1 + 1;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month1 + "/" + day1 + "/" + year1);

            String date = month1 + "/" + day1 + "/" + year1;
            discount_time_interval.setText(date);
        };

        DatePickerDialog dialog = new DatePickerDialog(
                DiscountActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void createDiscount(View view){
        String discountName = name.getEditText().getText().toString();
        Discount discount = new Discount(
                productSpinner.getSelectedItem().toString(),
                discountName,
                Double.parseDouble(rate.getEditText().getText().toString()),
                discount_start.getText().toString(),
                discount_end.getText().toString()
        );

        reference = FirebaseDatabase.getInstance().getReference("discounts");
        reference.child(discountName).setValue(discount);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}