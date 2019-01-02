package com.example.kylew.randomrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    private Button toMapButton;
    private Button toListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toMapButton = (Button) findViewById(R.id.Mapbutton);
        toListButton = (Button) findViewById(R.id.ListButton);

    final Intent mapIntent= new Intent(getApplicationContext(), MapActivity.class);
        toMapButton.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                startActivity(mapIntent);
                                           }
                                                                  });
    final Intent listIntent = new Intent (getApplication(), AddActivity.class);
        toListButton.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                startActivity(listIntent);
            }
        });
    }
}
