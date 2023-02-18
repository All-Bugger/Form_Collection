package com.example.formcollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Answer;
import com.example.formcollection.pojo.Form;
import com.example.formcollection.pojo.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyFormActivity extends AppCompatActivity {
    private List<Form> formList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);
        loadFormList();
        FormAdapter adapter = new FormAdapter(MyFormActivity.this, R.layout.form_item, formList);
        ListView listView = findViewById(R.id.form_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Form form = formList.get(position);
                Intent intent = new Intent(MyFormActivity.this, FillFormActivity.class);
                intent.putExtra("form",form);
                startActivity(intent);
            }
        });
    }

    private void loadFormList(){
        try {

            File directory = getBaseContext().getFilesDir();
            File formDir = new File(getBaseContext().getFilesDir()+"/form");
            if(!formDir.exists()) formDir.mkdir();
            File[] fileList = formDir.listFiles();
            assert fileList != null;
            for (File file: fileList) {
                Form form = new Form();
                ArrayList<Question> queList = new ArrayList<>();
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
                BufferedReader br = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();
                //读取json文件
                while((line = br.readLine()) != null){
                    builder.append(line);
                }
                br.close();
                isr.close();
                //导入json文件
                JSONObject json = new JSONObject(builder.toString());
                //读取表格
                form.setTitle(json.getString("name"));
                form.setFormId(json.getString("id"));
                JSONArray queArray = json.getJSONArray("content");

                //读取问题
                for(int i=0; i<queArray.length();i++){
                    Question que = new Question();
                    ArrayList<Answer> ansList = new ArrayList<>();
                    JSONObject que_object = queArray.getJSONObject(i);

                    que.setQuestionContent(que_object.getString("name"));
                    que.setType(que_object.getString("type"));
                    JSONArray ansArray = que_object.getJSONArray("options");
                    //读取答案
                    for(int j = 0; j < ansArray.length() ; j++){
                        Answer ans = new Answer();
                        ans.setAnswerContent(ansArray.get(j).toString());
                        ansList.add(ans);
                    }
                    que.setAnswers(ansList);
                    queList.add(que);
                }
                form.setQuestions(queList);
                formList.add(form);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}
