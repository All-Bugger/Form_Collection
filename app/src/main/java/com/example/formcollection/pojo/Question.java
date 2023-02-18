package com.example.formcollection.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {
    //题目id
    private String questionId;
    //类型（单选|多选）
    private String type;
    //题目
    private String questionContent;
    //选项
    private ArrayList<Answer> answers = new ArrayList<>();
    //是否回答
    private int questionState;

    protected Question(Parcel in) {
        questionId = in.readString();
        type = in.readString();
        questionContent = in.readString();
        questionState = in.readInt();

        in.readList(answers,Answer.class.getClassLoader());
        in.readTypedList(answers,Answer.CREATOR);
        getClass().getClassLoader();
        Thread.currentThread().getContextClassLoader();
        Answer.class.getClassLoader();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questionId);
        parcel.writeString(type);
        parcel.writeString(questionContent);
        parcel.writeInt(questionState);
        parcel.writeList(answers);
        parcel.writeTypedList(answers);
    }
}
