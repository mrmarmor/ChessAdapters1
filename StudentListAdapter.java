package com.example.guest.adapters.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Guest on 26/03/2017.
 */

public class StudentListAdapter extends BaseAdapter {
    private Context context;
    private List<String> textToShow;
    private List<Integer> imageToShow;
    private String viewToAdd;
    private static LayoutInflater inflater = null;

    public StudentListAdapter(){}
    public StudentListAdapter(Context mContext, List<String> strings, List<Integer> images, String view) {
        context = mContext;
        textToShow = strings;
        imageToShow = images;
        viewToAdd = view;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() { return textToShow.size(); }

    @Override
    public String getItem(int position) {
        if (position >=0)
            return textToShow.get(position);
        else
            return null;
    }
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
