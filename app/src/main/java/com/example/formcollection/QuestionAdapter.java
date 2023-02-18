package com.example.formcollection;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.formcollection.pojo.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {

    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
    }
}
