package com.example.mysecondapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysecondapp.R;
import com.example.mysecondapp.RMIInterface;
import com.example.mysecondapp.activity.toys.AddToyPriceActivity;
import com.example.mysecondapp.activity.toys.DeleteToyPriceActivity;
import com.example.mysecondapp.activity.toys.UpdateToyPriceActivity;
import com.example.mysecondapp.activity.toys.ViewToyActivity;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class DisplayMessageActivity extends AppCompatActivity {

    /**Shared preferences */
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        EditText mName = findViewById(R.id.editTextTextPersonName);
        Button btnLogout = findViewById(R.id.button2);
        Button btnVviewToys = findViewById(R.id.viewToys);
        Button btnAddToyPrice = findViewById(R.id.addToyPrice);
        Button btnUpdateToyPrice = findViewById(R.id.updateToyPrice);
        Button btnDeleteToyPrice = findViewById(R.id.deleteToyPrice);
        Button btnPrintReceipt = findViewById(R.id.printReceipt);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save username
                mEditor.clear();
                mEditor.commit();

                logoutUser();
            }
        });
        btnVviewToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewToys();
            }
        });
        btnAddToyPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToyPrice();
            }
        });
        btnUpdateToyPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateToyPrice();
            }
        });
        btnDeleteToyPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteToyPrice();
            }
        });
        btnPrintReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printReceipt();
            }
        });
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome "+message);

    }
    /** Called when the user taps the View Toys button */
    public void viewToys() {
        // Do something in response to button
        Intent intent = new Intent(this, ViewToyActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Add Toys button */
    public void addToyPrice() {
        // Do something in response to button
        //Intent intent = new Intent(this, AddToyPriceActivity.class);
        Intent intent = new Intent(this, AddToyPriceActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Update Toys button */
    public void updateToyPrice() {
        // Do something in response to button
      //  Intent intent = new Intent(this, updateToyPrice.class);
        Intent intent = new Intent(this, UpdateToyPriceActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Update Toys button */
    public void deleteToyPrice() {
        // Do something in response to button
       //Intent intent = new Intent(this, DeleteToyPriceActivity.class);
        Intent intent = new Intent(this, DeleteToyPriceActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Update Toys button */
    public void printReceipt() {
        // Do something in response to button
        Intent intent = new Intent(this, PrintReceiptActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Logout button */
    public void logoutUser() {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}