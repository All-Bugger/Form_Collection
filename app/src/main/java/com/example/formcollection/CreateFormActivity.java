package com.example.formcollection;

import android.content.Intent;
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

    private Form form = new Form();

    private List<Question> questionList = new ArrayList<>();

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    //随机生成6个数字长度的表单id
    public String takeFormId() {
        Random r = new Random();
        int id_int = r.nextInt(899999) + 100000;
        return String.valueOf(id_int);
    }

    public void init() {
        Question question = new Question();
        question.setQuestionId("1");
        question.setQuestionState(0);
        question.setType("1");
        question.setQuestionContent("请创建题目");
        question.setAnswers(new ArrayList<>());
        questionList.add(question);
    }

    public void createJSON() {

    }

    public void saveJSON() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_form);
        Form form = (Form) getIntent().getParcelableExtra("form");
        QuestionAdapter questionAdapter;
        if (form == null) {
            init();
            form = new Form();
            questionAdapter = new QuestionAdapter(CreateFormActivity.this, R.layout.create_form_list, questionList);
        } else {
            questionAdapter = new QuestionAdapter(CreateFormActivity.this, R.layout.create_form_list, form.getQuestions());
        }
        EditText form_name = (EditText) findViewById(R.id.form_name);
        ListView create_list_view = (ListView) findViewById(R.id.create_list_view);
        Button add_topic_btn = (Button) findViewById(R.id.add_topic);
        Button save = (Button) findViewById(R.id.save);
//        create_list_view.setAdapter(questionAdapter);
        form.setFormId(takeFormId());
        form.setTitle(form_name.toString());

        //点击跳转添加题目界面
        Form finalForm = form;
        add_topic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateFormActivity.this, AddOptionActivity.class);
                intent.putExtra("form", finalForm);//向添加界面传递form参数，来确保返回页面时能实现动态更新
                startActivity(intent);
            }
        });

        //点击保存按钮跳出弹窗显示表单id，并返回主页面
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createJSON();
                saveJSON();
                Intent intent = new Intent(CreateFormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
