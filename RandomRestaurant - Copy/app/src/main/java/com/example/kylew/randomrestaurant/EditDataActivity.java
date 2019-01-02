package com.example.kylew.randomrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete, btnBack;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        btnSave = (Button) findViewById(R.id.saveButton);
        btnDelete = (Button) findViewById(R.id.deleteButton1);
        editable_item = (EditText) findViewById(R.id.editText2);
        btnBack = (Button) findViewById(R.id.BackButton2);

        mDatabaseHelper = new DatabaseHelper(this);

        //get the intenet extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //Note: -1 is just the defalut value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_item.setText(selectedName);

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent backIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(backIntent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item, selectedID, selectedName);
                    Intent dataIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
                    startActivity(dataIntent);
                    toastMessage("Restaurant successfully saved!");
                }else {
                    toastMessage("You must enter a Restaurant");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //String item = editable_item.getText().toString();
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                Intent dataIntent2 = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(dataIntent2);
                toastMessage("Successfully removed from your List");
            }
        });
    }
    /*
    *customizable toast
    * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
