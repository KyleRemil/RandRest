package com.example.kyler.randrest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData, btnHome;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText1 = (EditText) findViewById(R.id.idEtNewRestName);
        btnAdd = (Button) findViewById(R.id.idBtnAdd1);
        btnViewData = (Button) findViewById(R.id.idBtnRestaurantList);
        btnHome = (Button) findViewById(R.id.idBtnBack1);

        mDatabaseHelper = new DatabaseHelper(this);

        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent HomeIntent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(HomeIntent);
            }
        });
        final Intent AddIntent = new Intent(AddActivity.this, ListDataActivity.class);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newEntry = editText1.getText().toString();
                if (editText1.length() != 0) {
                    AddData(newEntry);
                    editText1.setText("");
                    startActivity(AddIntent);
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });

        final Intent intent = new Intent(AddActivity.this, ListDataActivity.class);
        btnViewData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Restaurant Successfully add to Your List!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    /*
    * customizable toast
    * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
