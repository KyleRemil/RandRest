package com.example.kyler.randrest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.kyler.randrest.ListDataActivity.FIRST_VALUE;

public class ListRandomActivity extends AppCompatActivity {

    private TextView textdisp;
    private Button backBtn, NewRandBtn;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_random);

        textdisp = (TextView) findViewById(R.id.idTextView);
        backBtn = (Button) findViewById(R.id.idBtnBackToList);
        NewRandBtn = (Button) findViewById(R.id.idBtnDifRest);

        final Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        //retrieves the Restaurant name sent from ListDataActivity
        String RandRestName = getIntent().getStringExtra(FIRST_VALUE);
        textdisp.setText(RandRestName);
        //displays the restarant name in the textdisp TextView


        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        NewRandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleMapsActivity = new Intent(Intent.ACTION_VIEW);
                String destination = textdisp.getText().toString();
                googleMapsActivity.setData(Uri.parse("geo:0,0?q= " + destination));
                startActivity(googleMapsActivity);
            }
        });
    }
}

