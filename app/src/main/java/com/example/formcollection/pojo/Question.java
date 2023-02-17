package com.example.formcollection.pojo;

import java.util.ArrayList;

public class Question {
    //题目id
    private String questionId;
    //类型（单选|多选）
    private String type;
    //题目
    private String questionContent;
    //选项
    private ArrayList<Answer> answers;
    //是否回答
    private int questionState;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getQuestionState() {
        return questionState;
    }

    public void setQuestionState(int questionState) {
        this.questionState = questionState;
    }

    public Question(String questionId, String type, String questionContent, ArrayList<Answer> answers, int questionState) {
        this.questionId = questionId;
        this.type = type;
        this.questionContent = questionContent;
        this.answers = answers;
        this.questionState = questionState;
    }

    public Question() {
    }
}
