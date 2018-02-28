package com.ilkayaktas.projectname.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ilkayaktas.projectname.model.app.Dummy;

import java.util.List;

/**
 * Created by aselsan on 14.02.2018 at 08:22.
 */

public class CustomSpinnerAdapter extends ArrayAdapter {
    private Context context;
    private List<Dummy> streams;
    private int resource;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Dummy> streams) {
        super(context, resource, streams);
        this.context = context;
        this.streams = streams;
        this.resource = resource;
    }

    @Override
    public int getCount(){
        return streams.size();
    }

    @Override
    public Dummy getItem(int position){
        return streams.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView spinner = (TextView) ((AppCompatActivity)context).getLayoutInflater().inflate(resource, null);
        spinner.setText(getItem(position).name);
        return spinner;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView spinner = (TextView) ((AppCompatActivity)context).getLayoutInflater().inflate(resource, null);
        spinner.setText(getItem(position).name);
        spinner.setPadding(30,20,10,20);
        return spinner;
    }
}
