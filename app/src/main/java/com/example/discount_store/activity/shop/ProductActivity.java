package com.example.discount_store.activity.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discount_store.R;
import com.example.discount_store.model.Product;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    TextInputLayout name, amount;
    List<String> categories = Arrays.asList("Technik", "Sport","Moda","Music", "Food");
    DatabaseReference reference;
    Spinner type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        name = findViewById(R.id.product_name);
        amount = findViewById(R.id.product_amount);
        type = findViewById(R.id.product_type);

        // Spinner click listener
        type.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        type.setAdapter(dataAdapter);
    }

    public void createProduct(View view){
        reference = FirebaseDatabase.getInstance().getReference("products");
        String productName = name.getEditText().getText().toString();
        Product product = new Product(
                name.getEditText().getText().toString(),
                Integer.parseInt(amount.getEditText().getText().toString()),
                "shop",
                type.getSelectedItem().toString()
        );
        reference.child(productName).setValue(product);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}