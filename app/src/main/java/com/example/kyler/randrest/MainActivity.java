package com.example.kyler.randrest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button mapButton, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    public void init() {
        mapButton = (Button) findViewById(R.id.btnMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent mapActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapActivity);
            }
        });
        btnList = (Button) findViewById(R.id.idBtnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listActivity = new Intent(getApplicationContext(), ListDataActivity.class);
                startActivity(listActivity);
            }
        });
    }
}
