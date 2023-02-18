package com.example.formcollection.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Form implements Parcelable
{
    //问卷id
    private String FormId;
    //问卷主题
    private String title;
    //题目
    private ArrayList<Question> questions;

    protected Form(Parcel in) {
        FormId = in.readString();
        title = in.readString();
    }

    public static final Creator<Form> CREATOR = new Creator<Form>() {
        @Override
        public Form createFromParcel(Parcel in) {
            return new Form(in);
        }

        @Override
        public Form[] newArray(int size) {
            return new Form[size];
        }
    };

    public String getFormId() {
        return FormId;
    }

    public void setFormId(String formId) {
        FormId = formId;
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
        this.title = title;
        this.questions = questions;
    }

    public Form() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(FormId);
        parcel.writeString(title);
        parcel.writeList(questions);
    }
}
