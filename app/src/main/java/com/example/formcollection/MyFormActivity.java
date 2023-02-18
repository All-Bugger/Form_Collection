package com.example.formcollection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Form;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
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

    private void loadFormList(){
        try {
            Form form = new Form();
            File file = new File(
                    getApplicationContext().getFilesDir().getAbsolutePath()+ "/form/form.json");
            if (!file.exists()) file.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            jsonReader.beginObject();
            while (jsonReader.hasNext()){
                if(jsonReader.nextName().equals("name")){
                    form.setTitle(jsonReader.nextString());
                }
                if(jsonReader.nextName().equals("id")){
                    form.setFormId(jsonReader.nextString());
                }
                if(jsonReader.nextName().equals("content")){

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
