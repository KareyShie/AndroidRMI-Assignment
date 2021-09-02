package com.example.mysecondapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.RMIInterface;
import com.example.mysecondapp.adapter.CustomAdapterPrintReceipt;
import com.example.mysecondapp.model.Toy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class PrintReceiptActivity extends AppCompatActivity {

    Client client;
    RMIInterface rmiInterface;
    boolean isConnected=false;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_receipt);
        mPreferences  = PreferenceManager.getDefaultSharedPreferences(this);
        String name= mPreferences.getString(getString(R.string.edit_message),"");
        String message="Hi " + name + ". Here is your receipt.";

        TextView nametextview=findViewById(R.id.textViewViewToys);
        nametextview.setText(message);
        if(!isConnected)connect();
        String  msg = rmiInterface.getAllToys();
        Gson gson = new Gson();
        List<Toy> toys = gson.fromJson(msg,new TypeToken<List<Toy>>(){}.getType());

        //show recyclerview list
        RecyclerView recyclerView = findViewById(R.id.rvToyReceipt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapterPrintReceipt(toys));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void connect() {
        try{
            CallHandler callHandler = new CallHandler();
            client = new Client("192.168.100.12", 1099, callHandler);
            rmiInterface = (RMIInterface) client.getGlobal(RMIInterface.class);
            isConnected=true;
            Toast.makeText(this, "RMI connected.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("RMIOUT", e.getMessage());
            Toast.makeText(this, "RMI connection failed.", Toast.LENGTH_SHORT).show();
        }
    }
}