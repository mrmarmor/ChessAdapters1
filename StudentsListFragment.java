package com.example.guest.adapters.Fragments;


import android.content.ContentResolver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.guest.adapters.R;
import com.example.guest.adapters.Adapters.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentsListFragment extends Fragment {
    private final String USER1_PIC_URI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEBT-oOeofRCgxDdGhyIYpWCWmJefDV9UefDSAyBpegYu5VtmI"
            , USER2_PIC_URI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFE5uV7FIVj5SAIeYiKE-loJObUi8ED3mp8i-UpxEVgHknOPZ4Lw"
            , USER3_PIC_URI = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSofASViqUi1-2YOexWuEKZSxnOw7dXW6851Ew2Cxsjlbjd4RsIBQ";
    private final String TEXT_VIEW = "textView", IMAGE_VIEW = "imageView", VIDEO_VIEW = "videoView";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_students_list_with_adapter, container, false);

        List<String> studentsNames = addData(new String[]{"בונדר", "גימרה", "ארטיום"});

        List<Date> messagesDates = addData(new Date[]{new Date("23/03/2016"), new Date("27/03/2015"), new Date("30/03/2015")});//new ArrayList<>();

        List</*Uri*/Drawable> studentsPics = addData(new Drawable[]{getResources().getDrawable(R.drawable.mario)
            , getResources().getDrawable(R.drawable.pinguin), getResources().getDrawable(R.drawable.soldier)});

        List<Object> versatileContent = addData(new Object[]{new String("   היי כולם")
            , drawableToUri(getResources().getDrawable(R.drawable.mario))
            , Uri.parse("https://ia800309.us.archive.org/35/items/WorkToFishtestwmv/test_wmv.ogv")});//new ArrayList<>();
        //versatileContent.add(new String("   היי כולם"));
        //versatileContent.add(drawableToUri(getResources().getDrawable(R.drawable.mario))/*Uri.parse(USER1_PIC_URI)*/);
        //versatileContent.add(Uri.parse("https://ia800309.us.archive.org/35/items/WorkToFishtestwmv/test_wmv.ogv"));

        List<String> viewsToAdd = addData(new String[]{TEXT_VIEW, IMAGE_VIEW, VIDEO_VIEW});

        ListView lvStudents = (ListView)v.findViewById(R.id.lvWithAdapter);
        lvStudents.setAdapter(new StudentListAdapter<>(getActivity(), studentsNames, messagesDates
            , studentsPics, versatileContent, viewsToAdd));

        return v;
    }

    public static Uri drawableToUri(Drawable d){
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + d//getResources().getResourcePackageName(d)
                + '/' + /*getResources().getResourceTypeName(d)*/d + '/' + d/*getResources().getResourceEntryName(R.drawable.ic_launcher)*/ );
        return imageUri;
    }

    public static <T> List<T> addData(T[] dataObjects){
        List<T> studentsDataObjects = new ArrayList<>();
        for (T dataObject : dataObjects)
            studentsDataObjects.add(dataObject);

        return studentsDataObjects;
    }

}
