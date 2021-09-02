package com.example.mysecondapp.activity.toys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysecondapp.R;
import com.example.mysecondapp.RMIInterface;
import com.example.mysecondapp.adapter.CustomAdapterUpdateToyPrice;
import com.example.mysecondapp.model.Toy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class UpdateToyPriceActivity extends AppCompatActivity {

    Client client;
    RMIInterface rmiInterface;
    boolean isConnected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_toy_price);

        if(!isConnected)connect();
        String  msg = rmiInterface.getAllToys();
        Gson gson = new Gson();
        List<Toy> toys = gson.fromJson(msg,new TypeToken<List<Toy>>(){}.getType());

        //show recyclerview list
        RecyclerView recyclerView = findViewById(R.id.rvToyUpdate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new CustomAdapterUpdateToyPrice(toys, new CustomAdapterUpdateToyPrice.OnItemClickListener() {
            @Override
            public void onItemClick(Toy toy) {
                // Call the helper method.
                newPrice(toy);
            }
        }));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void connect() {
        try{
            CallHandler callHandler = new CallHandler();
            client = new Client("localhost", 1099, callHandler);
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

    /**
     * Helper method to set the new price.
     * Shows an input dialog where the new price is entered
     * @param toy whose price is to be updated
     */
    private void newPrice(final Toy toy) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(UpdateToyPriceActivity.this);
        LayoutInflater inflater = UpdateToyPriceActivity.this.getLayoutInflater();

        // We are using a custom layout in the dialog.
        // A simple edit text set to the dialog
        View priceView = inflater.inflate(R.layout.enter_value, null);
        builder.setView(priceView);

        final EditText phoneTv = priceView.findViewById(R.id.edit_value);
        phoneTv.setHint("Enter Price");

        builder.setTitle("Update Price");
        builder.setMessage("Please enter the new price for " + toy.getName());
        // When OK button is clicked, we are supposed to save the new price.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String price = phoneTv.getText().toString();
                int newPrice = Integer.parseInt(price);
                rmiInterface.updateToyPrice(newPrice, toy.getId());
                // Finish or go to all toys page? Depends on what you feel like is the
                // best implementation.
                finish();
            }
        });
        // Cancel the dialog if the Cancel button is clicked.
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // This section is responsible for showing the dialog.
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}