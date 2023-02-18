package com.example.formcollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Form;

import java.util.ArrayList;

public class AddOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form_option);
        Button add_option_btn = (Button) findViewById(R.id.add_option);
        Button save_option_btn = (Button) findViewById(R.id.save_option);
        Button back_btn = (Button) findViewById(R.id.back);
        ArrayList<EditText> options = new ArrayList<>();
        Form form = getIntent().getParcelableExtra("form");

        add_option_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        save_option_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText topic_name = (EditText) findViewById(R.id.topic_name);


                Intent intent = new Intent(AddOptionActivity.this,CreateFormActivity.class);
                intent.putExtra("form",form);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddOptionActivity.this,CreateFormActivity.class);
                intent.putExtra("form",form);
                startActivity(intent);
            }
        });
    }
}
