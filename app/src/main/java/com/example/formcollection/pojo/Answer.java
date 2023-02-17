package com.example.formcollection.pojo;

public class Answer {
    //答案id
    private String answerId;
    //答案主体
    private String answerContent;
    //答案状态
    private int answerState;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getAnswerState() {
        return answerState;
    }

    public void setAnswerState(int answerState) {
        this.answerState = answerState;
    }

    public Answer(String answerId, String answerContent, int answerState) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.answerState = answerState;
    }

    public Answer() {
    }
}
