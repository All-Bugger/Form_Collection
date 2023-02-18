package com.example.formcollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyFormActivity extends AppCompatActivity {
    private List<Form> formList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);

        FormAdapter adapter = new FormAdapter(MyFormActivity.this, R.layout.form_item, formList);
        ListView listView = findViewById(R.id.form_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Form form = formList.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MyFormActivity.this, FillFormActivity.class);
                bundle.putSerializable("form", (Serializable) form);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
