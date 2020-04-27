package com.example.curie.fairbanks_03;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;



public class MainActivity extends Activity {

    Button contButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contButton = (Button) findViewById(R.id.contButton);
        contButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DeviceList.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}