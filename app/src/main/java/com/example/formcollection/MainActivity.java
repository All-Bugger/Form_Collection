package com.example.formcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createFormBtn = (Button)findViewById(R.id.create_form);
        Button fillFormBtn = (Button) findViewById(R.id.fill_form);
        Button myFormBtn = (Button) findViewById(R.id.myForm);

        createFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateFormActivity.class);
                startActivity(intent);
            }
        });
        fillFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FillFormActivity.class);
                startActivity(intent);
            }
        });
        myFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyFormActivity.class);
                startActivity(intent);
            }
        });
    }
}