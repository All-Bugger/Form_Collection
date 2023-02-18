package com.example.formcollection;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.formcollection.pojo.Answer;
import com.example.formcollection.pojo.Form;
import com.example.formcollection.pojo.Question;

import java.util.ArrayList;

public class FillFormActivity extends AppCompatActivity {
    private Form form;
    private ArrayList<Question> que_list = new ArrayList<>();
    private LayoutInflater inflater;
    private ArrayList<View> ans_view_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        form = (Form)getIntent().getParcelableExtra("form");
        initData();
        initView();
    }

    private void initData(){
        Question question = new Question();
        question.setQuestionId("1");
        question.setQuestionState(0);
        question.setType("单选");
        question.setQuestionContent("请创建题目");
        Answer a1 = new Answer("a","aaaaaaaaaa",0);
        Answer a2 = new Answer("b","bbbbbbbbbb",0);
        Answer a3 = new Answer("c","cccccccccc",0);
        Answer a4 = new Answer("d","dddddddddd",0);
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(a1);
        answers.add(a2);
        answers.add(a3);
        answers.add(a4);
        question.setAnswers(answers);
        que_list.add(question);
        que_list.add(question);
        que_list.add(question);
        que_list.add(question);
        que_list.add(question);
        que_list.add(question);
        que_list.add(question);
    }

    private void initView() {
        que_list = form.getQuestions();
        LinearLayout form_layout = findViewById(R.id.form_content);          //读取添加问题的布局
        TextView form_title = findViewById(R.id.from_title);
        form_title.setText(form.getTitle());                    //设置表格标题
        //加载问题
        for(int i = 0; i < que_list.size(); i++){
            View que_view = inflater.inflate(R.layout.question, null);
            TextView que_index = que_view.findViewById(R.id.question_index);
            TextView que_content = que_view.findViewById(R.id.question);
            //设置问题序号、内容
            String index = String.valueOf(i+1)+"、";
            que_index.setText(index);
            que_content.setText(que_list.get(i).getQuestionContent());
            //加载答案列表
            ArrayList<Answer> ans_list = que_list.get(i).getAnswers();
            //定义各种所需布局
            LinearLayout ans_layout = que_view.findViewById(R.id.answer);
            View ans_view = inflater.inflate(R.layout.answer,null);
            LinearLayout layout_in_ans = ans_view.findViewById(R.id.answer_layout);
            //加载答案
            if(que_list.get(i).getType().equals("单选")){
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setId(R.id.radio_group);
                for(int j = 0; j < ans_list.size(); j++){
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(ans_list.get(j).getAnswerContent());
                    radioGroup.addView(radioButton);
                    radioButton.setOnClickListener(new answerItemOnClickListener(i));
                }
                layout_in_ans.addView(radioGroup);
            }else{
                for(int j = 0; j < ans_list.size(); j++){
                    CheckBox checkBox = new CheckBox(this);
                    checkBox.setText(ans_list.get(j).getAnswerContent());
                    setCheckBoxId(checkBox,j);
                    layout_in_ans.addView(checkBox);
                    checkBox.setOnClickListener(new answerItemOnClickListener(i));
                }
            }
            //将答案添加到问题布局，问题添加到表单布局
            ans_layout.addView(ans_view);
            form_layout.addView(que_view);
            //将各个问题答案的View实例加入list，方便读取各问题作答情况
            ans_view_list.add(ans_view);
            //绑定提交按钮点击事件
            findViewById(R.id.submit).setOnClickListener(new submitButtonOnClickListener());
        }

    }

    //设置多选按钮ID
    private void setCheckBoxId(CheckBox checkBox, int j){
        switch (j){
            case 0: checkBox.setId(R.id.check1);
                break;
            case 1: checkBox.setId(R.id.check2);
                break;
            case 2: checkBox.setId(R.id.check3);
                break;
            case 3: checkBox.setId(R.id.check4);
                break;
            default: break;
        }
    }

    //多选按钮是否选择
    private boolean isChecked(int i){
        CheckBox checkBox;
        for(int j = 0; j < que_list.get(i).getAnswers().size(); j++){
            switch (j){
                case 0: checkBox = ans_view_list.get(i).findViewById(R.id.check1);
                    if(checkBox.isChecked()) return true;
                    break;
                case 1: checkBox = ans_view_list.get(i).findViewById(R.id.check2);
                    if(checkBox.isChecked()) return true;
                    break;
                case 2: checkBox = ans_view_list.get(i).findViewById(R.id.check3);
                    if(checkBox.isChecked()) return true;
                    break;
                case 3: checkBox = ans_view_list.get(i).findViewById(R.id.check4);
                    if(checkBox.isChecked()) return true;
                    break;
                default: break;
            }
        }
        return false;
    }

    //回答按钮点击事件
    class answerItemOnClickListener implements View.OnClickListener {
        private int i;
        public answerItemOnClickListener(int i) {
            this.i = i;
        }
        //点击时更新问题状态
        @Override
        public void onClick(View arg0) {
            if(que_list.get(i).getType().equals("单选")){
                RadioGroup radioGroup = ans_view_list.get(i).findViewById(R.id.radio_group);
                RadioButton radioButton = ans_view_list.get(i).findViewById(radioGroup.getCheckedRadioButtonId());
                if(radioButton!=null) que_list.get(i).setQuestionState(1);
                else que_list.get(i).setQuestionState(0);
            } else {
                if(isChecked(i)) que_list.get(i).setQuestionState(1);
                else que_list.get(i).setQuestionState(0);
            }
        }
    }

    //提交按钮点击事件
    class submitButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //判断答卷是否完成
            boolean isDone = true;
            for (Question q : que_list) {
                if(q.getQuestionState()==0){
                    isDone = false;
                    break;
                }
            }
            if(isDone){
                //将答卷写入json
            } else {
                //提示答卷未完成
                Toast.makeText(getApplication(), R.string.uncompleted_err, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
