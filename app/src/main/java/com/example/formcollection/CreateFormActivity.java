package com.example.formcollection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Answer;
import com.example.formcollection.pojo.Form;
import com.example.formcollection.pojo.Question;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateFormActivity extends AppCompatActivity {

    private Form form = new Form();

    private List<Question> questionList = new ArrayList<>();

    private String fileName;

    private JSONObject jsonObject = new JSONObject();

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
        Answer a1 = new Answer("a", "aaaaaaaaaa", 0);
        Answer a2 = new Answer("b", "bbbbbbbbbb", 0);
        Answer a3 = new Answer("c", "cccccccccc", 0);
        Answer a4 = new Answer("d", "dddddddddd", 0);
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        question.setAnswers(answers);
        questionList.add(question);
    }

    public void createFile() throws IOException {
        fileName = "/form/" + form.getFormId() + ".json";
        File file = new File(Environment.getExternalStorageDirectory() + fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
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
        ListView create_list_view = (ListView) findViewById(R.id.create_list_view);
        Button add_topic_btn = (Button) findViewById(R.id.add_topic);
        Button save = (Button) findViewById(R.id.save);
        create_list_view.setAdapter(questionAdapter);
        form.setFormId(takeFormId());

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
        Form finalForm1 = form;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalForm1.getQuestions().size()!=0){
                    try {
                        EditText form_name = (EditText) findViewById(R.id.form_name);
                        finalForm1.setTitle(form_name.getText().toString());
                        jsonObject.put("name", finalForm1.getTitle());
                        jsonObject.put("id", finalForm1.getFormId());
                        jsonObject.put("type", "0");
                        JSONArray FORMS = new JSONArray();

                        List<Question> questions = finalForm1.getQuestions();
                        for (int i = 0; i < questions.size(); i++) {
                            JSONObject FORM = new JSONObject();
                            FORM.put("name", questions.get(i).getQuestionContent());
                            FORM.put("id", questions.get(i).getQuestionId());
                            FORM.put("type", questions.get(i).getType());
                            ArrayList<Answer> answers = questions.get(i).getAnswers();
                            JSONArray jsonArray = new JSONArray();
                            jsonArray.add(answers.get(0).getAnswerContent());
                            jsonArray.add(answers.get(1).getAnswerContent());
                            jsonArray.add(answers.get(2).getAnswerContent());
                            jsonArray.add(answers.get(3).getAnswerContent());
                            FORM.put("options",jsonArray);
                            FORMS.add(FORM);
                        }
                        jsonObject.put("content",FORMS);

                        //json文件创建
                        fileName = "/form/" + finalForm1.getFormId() + ".json";
                        File file = new File(
                                getApplicationContext().getFilesDir().getAbsolutePath()+ fileName);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));

                        //弹窗
                        AlertDialog alertDialog1 = new AlertDialog.Builder(CreateFormActivity.this)
                                .setMessage("表格id为：" + finalForm1.getFormId() )//内容
                                .create();
                        alertDialog1.show();
                        //弹窗显示三秒
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(3000);//休眠3秒
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(CreateFormActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }.start();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    AlertDialog alertDialog1 = new AlertDialog.Builder(CreateFormActivity.this)
                            .setMessage("请至少加入一个问题！" )//内容
                            .create();
                    alertDialog1.show();
                }
            }
        });
    }
}
