package com.example.formcollection;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Answer;
import com.example.formcollection.pojo.Form;
import com.example.formcollection.pojo.Question;

import java.util.ArrayList;

public class FillFormActivity extends AppCompatActivity {
    private LinearLayout form_layout;
    private Form form;
    private ArrayList<Answer> answers_list;
    private ArrayList<Question> questions_list;
    private View question_view;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        initView(form);
    }

    private void initView(Form form) {
        form_layout = findViewById(R.id.form_content);          //读取添加问题的布局
        TextView form_title = findViewById(R.id.from_title);
        form_title.setText(form.getTitle());                    //设置表格标题

        questions_list = form.getQuestions();
        for (int i = 0; i < questions_list.size(); i++) {

        }
    }
}
