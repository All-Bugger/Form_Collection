package com.example.formcollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.formcollection.pojo.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {
    private int resourceId;

    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Question question = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView topic_name = (TextView) view.findViewById(R.id.topic_name_list);
        RadioButton r1 = (RadioButton) view.findViewById(R.id.option_1);
        RadioButton r2 = (RadioButton) view.findViewById(R.id.option_2);
        RadioButton r3 = (RadioButton) view.findViewById(R.id.option_3);
        RadioButton r4 = (RadioButton) view.findViewById(R.id.option_4);
        topic_name.setText(question.getQuestionContent());
        r1.setText(question.getAnswers().get(0).getAnswerContent());
        r2.setText(question.getAnswers().get(1).getAnswerContent());
        r3.setText(question.getAnswers().get(2).getAnswerContent());
        r4.setText(question.getAnswers().get(3).getAnswerContent());
        return view;
    }
}
