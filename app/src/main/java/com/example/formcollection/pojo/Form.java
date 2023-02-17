package com.example.formcollection.pojo;

import android.widget.ArrayAdapter;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Form {
    //问卷id
    private String FormId;
    //问卷状态
    private String status;
    //问卷主题
    private String title;
    //题目
    private ArrayList<Question> questions;

    public String getFormId() {
        return FormId;
    }

    public void setFormId(String formId) {
        FormId = formId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Form(String formId, String status, String title, ArrayList<Question> questions) {
        FormId = formId;
        this.status = status;
        this.title = title;
        this.questions = questions;
    }

    public Form() {
    }
}
