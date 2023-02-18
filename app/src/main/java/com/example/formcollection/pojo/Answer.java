package com.example.formcollection.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    //答案id
    private String answerId;
    //答案主体
    private String answerContent;
    //答案状态
    private int answerState;

    protected Answer(Parcel in) {
        answerId = in.readString();
        answerContent = in.readString();
        answerState = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answerId);
        dest.writeString(answerContent);
        dest.writeInt(answerState);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

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
