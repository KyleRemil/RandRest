package com.example.kylew.randomrestaurant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class ListDataActivity extends AppCompatActivity {

    private Button btnAdd3, hideKey, btnRand, Homebtn;
    private EditText editText3;

    public final static String FIRST_VALUE = "com.studentdomainname.usingintents.FIRST_VALUE";
    public final static String SECOUND_VALUE = "com.studentdomainname.usingintents.SECOUND_VALUE";
    //allows one to send data to another activity page
    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        mListView = (ListView) findViewById(R.id.listView2);

        editText3 = (EditText) findViewById(R.id.editText2);
        btnAdd3 = (Button) findViewById(R.id.addButton2);
        hideKey = (Button) findViewById(R.id.hideKeyButton2);
        btnRand = (Button) findViewById(R.id.RandButton2);
        Homebtn = (Button) findViewById(R.id.HomeButton);

        final Intent homeIntent = new Intent(ListDataActivity.this, HomePageActivity.class);
        Homebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(homeIntent);
            }
        });

        mDatabaseHelper = new DatabaseHelper(this);
        final Intent RefIntent = new Intent(ListDataActivity.this, ListDataActivity.class);
        btnAdd3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newEntry = editText3.getText().toString();
                if (editText3.length() != 0) {
                    AddData(newEntry);
                    editText3.setText("");
                    InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); //ability to hide keyboard
                    imm.hideSoftInputFromWindow(editText3.getWindowToken(),0);                                  //on button click
                } else {
                    toastMessage("You must put something in the text field!");
                }
             startActivity(RefIntent);
            }
        });
        hideKey.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);     //ability to hid keyboard
                imm.hideSoftInputFromWindow(editText3.getWindowToken(),0);                                      //on button click
            }
        });

        populateListView();
    }
    private void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1,(which is labled COL1)
            //then add it to the arraylist
            listData.add(data.getString(1));
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);

        Random randomizer = new Random();
        final String random = listData.get(randomizer.nextInt(listData.size()));
        //selects a Restaurant randomly from the arrayList

        final String random2 = listData.get(randomizer.nextInt(listData.size()));

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemCLick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemID(name); // get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                } else {
                    toastMessage("No ID associated with that name");
                }
            };
        });
        final Intent randIntent = new Intent(ListDataActivity.this, ListRandomActivity.class);
        btnRand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                randIntent.putExtra(FIRST_VALUE, (random)); //sends the randomly selected restaurant to ListRandomActivity.java
                randIntent.putExtra(SECOUND_VALUE, (random2));
                startActivity(randIntent);
            }
        });
    }
    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Restaurant Successfully added!");
        } else {
            toastMessage("Something went wrong, please try again");
        }
    }
    /*
    *custamizable toast
    * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
