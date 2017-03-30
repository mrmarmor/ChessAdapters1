package com.example.guest.adapters.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.adapters.Adapters.StudentListAdapter;
import com.example.guest.adapters.R;

import java.util.Date;
import java.util.List;

public class StudentsRecyclerFragment extends Fragment {
    private final String TEXT_VIEW = "textView", IMAGE_VIEW = "imageView", VIDEO_VIEW = "videoView";

    public StudentsRecyclerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_recycler, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            List<String> studentsNames = StudentsListFragment.addData(new String[]{"בונדר", "גימרה", "ארטיום"});

            List<Date> messagesDates = StudentsListFragment.addData(new Date[]{new Date("23/03/2016"), new Date("27/03/2015"), new Date("30/03/2015")});//new ArrayList<>();

            List</*Uri*/Drawable> studentsPics = StudentsListFragment.addData(
                    new Drawable[]{getResources().getDrawable(R.drawable.mario)
                    , getResources().getDrawable(R.drawable.pinguin), getResources().getDrawable(R.drawable.soldier)});

            List<Object> versatileContent = StudentsListFragment.addData(new Object[]{
                    new String("   היי כולם"), StudentsListFragment.drawableToUri(
                    getResources().getDrawable(R.drawable.mario))
                    , Uri.parse("https://ia800309.us.archive.org/35/items/WorkToFishtestwmv/test_wmv.ogv")});//new ArrayList<>();

            List<String> viewsToAdd = StudentsListFragment.addData(new String[]{TEXT_VIEW, IMAGE_VIEW, VIDEO_VIEW});

            RecyclerView recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new StudentRecyclerViewAdapter(context, studentsNames
                    , messagesDates, studentsPics, versatileContent, viewsToAdd));
        }

        return view;
    }

}
