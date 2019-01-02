package com.example.kyler.randrest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete, btnBack, btnTakeMeThere;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        btnSave = (Button) findViewById(R.id.idBtnSave);
        btnDelete = (Button) findViewById(R.id.idBtnDelete);
        editable_item = (EditText) findViewById(R.id.idEtRestaurantNameEdit);
        btnBack = (Button) findViewById(R.id.idBtnBack);

        mDatabaseHelper = new DatabaseHelper(this);

        //get the intenet extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //Note: -1 is just the defalut value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_item.setText(selectedName);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(backIntent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if (!item.equals("")) {
                    mDatabaseHelper.updateName(item, selectedID, selectedName);
                    Intent dataIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
                    startActivity(dataIntent);
                    Toast.makeText(getApplicationContext(), "Restaurant successfully saved!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You must enter a Restaurant", Toast.LENGTH_SHORT);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String item = editable_item.getText().toString();
                mDatabaseHelper.deleteName(selectedID, selectedName);
                editable_item.setText("");
                Intent dataIntent2 = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(dataIntent2);
                Toast.makeText(getApplicationContext(), "Successfully removed from your List", Toast.LENGTH_LONG);
            }
        });
        btnTakeMeThere = (Button) findViewById(R.id.idBtnTakeMeThere);
        btnTakeMeThere.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent googleMapsActivity = new Intent(Intent.ACTION_VIEW);
            String destination = editable_item.getText().toString();
            googleMapsActivity.setData(Uri.parse("geo:0,0?q= " + destination));
            startActivity(googleMapsActivity);
        }
    });
    }
}