package com.example.kylew.randomrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListRandomActivity extends AppCompatActivity {

    private TextView textdisp;
    private Button backBtn, NewRandBtn;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_random);

        textdisp = (TextView) findViewById(R.id.RandtextView);
        backBtn = (Button) findViewById(R.id.backButton2);
        NewRandBtn = (Button) findViewById(R.id.NewRandButton);

        final Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        //retrieves the Restaurant name sent from ListDataActivity
        String RandRestName = getIntent().getStringExtra(ListDataActivity.FIRST_VALUE);
        textdisp.setText(RandRestName);
        //displays the restarant name in the textdisp TextView

   /*     final Intent NewIntent = new Intent(getApplicationContext(), ListRandomActivity.class);
        NewRandBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(NewIntent);
            }
        }); */

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}

