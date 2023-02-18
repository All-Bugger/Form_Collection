package com.example.formcollection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Form;
import com.example.formcollection.pojo.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateFormActivity extends AppCompatActivity {

    private List<Question> questions = new ArrayList<>();

    private Form form = new Form();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    //生成
    public String takeFormId(){
        int id_int = 0;
        Random r = new Random();
        id_int = r.nextInt(1099999)-1000000;
        return String.valueOf(id_int);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_form);
        QuestionAdapter questionAdapter = new QuestionAdapter(CreateFormActivity.this,R.layout.create_form_list,questions);
        EditText form_name = (EditText) findViewById(R.id.form_name);
        ListView create_list_view = (ListView) findViewById(R.id.create_list_view);
        Button add_topic_btn = (Button) findViewById(R.id.add_topic);
        Button save = (Button) findViewById(R.id.save);

        create_list_view.setAdapter(questionAdapter);


        //点击保存按钮跳出弹窗显示表单id，并返回主页面
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
