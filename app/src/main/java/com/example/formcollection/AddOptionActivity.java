package com.example.formcollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Answer;
import com.example.formcollection.pojo.Form;
import com.example.formcollection.pojo.Question;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AddOptionActivity extends AppCompatActivity {

    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form_option);
        Button save_option_btn = (Button) findViewById(R.id.save_option);
        Button back_btn = (Button) findViewById(R.id.back);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        RadioButton r1 = (RadioButton) findViewById(R.id.r1);
        RadioButton r2 = (RadioButton) findViewById(R.id.r2);
        Form form = getIntent().getParcelableExtra("form");

        //获取题目类型
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == r1.getId()) {
                    type = "单选";
                } else if (i == r2.getId()) {
                    type = "多选";
                }
            }
        });

        //将参数置入form中传回createForm活动
        save_option_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText topic_name = (EditText) findViewById(R.id.topic_name);
                EditText r1Con = (EditText) findViewById(R.id.r1_content);
                EditText r2Con = (EditText) findViewById(R.id.r2_content);
                EditText r3Con = (EditText) findViewById(R.id.r3_content);
                EditText r4Con = (EditText) findViewById(R.id.r4_content);

                ArrayList<Answer> arrayList = new ArrayList<>();
                Answer a1 = new Answer("a", r1Con.getText().toString(), 0);
                Answer a2 = new Answer("b", r2Con.getText().toString(), 0);
                Answer a3 = new Answer("c", r3Con.getText().toString(), 0);
                Answer a4 = new Answer("d", r4Con.getText().toString(), 0);
                arrayList.add(a1);
                arrayList.add(a2);
                arrayList.add(a3);
                arrayList.add(a4);

                int i;
                if(form.getQuestions() != null){
                    i = form.getQuestions().size();
                }else{
                    i = 0;
                }
                Question q = new Question();
                q.setQuestionId(String.valueOf(i + 1));
                q.setQuestionContent(topic_name.getText().toString());
                q.setType(type);
                q.setQuestionState(0);
                q.setAnswers(arrayList);

                ArrayList<Question> questions = new ArrayList<>();
                if(form.getQuestions() != null){
                    questions = form.getQuestions();
                }else{
                    questions = new ArrayList<>();
                }
                questions.add(q);
                form.setQuestions(questions);

                Intent intent = new Intent(AddOptionActivity.this, CreateFormActivity.class);
                intent.putExtra("form", form);
                startActivity(intent);
            }
        });

        //点击back按钮返回上一页，并将form参数传回
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddOptionActivity.this, CreateFormActivity.class);
                intent.putExtra("form", form);
                startActivity(intent);
            }
        });
    }
}
