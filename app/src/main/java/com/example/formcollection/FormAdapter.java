package com.example.formcollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.formcollection.pojo.Form;

import java.util.List;

public class FormAdapter extends ArrayAdapter<Form> {
    private int resourceId;

    public FormAdapter(Context context, int textViewResourceId, List<Form> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Form form = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.formTitle = (TextView) view.findViewById(R.id.from_title);
            viewHolder.formID = (TextView) view.findViewById(R.id.form_id);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.formTitle.setText(form.getTitle());
        viewHolder.formID.setText(form.getFormId());

        return view;
    }

    class ViewHolder {
        TextView formTitle;
        TextView formID;
    }
}
