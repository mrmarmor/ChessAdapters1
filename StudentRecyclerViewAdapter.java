package com.example.guest.adapters.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.guest.adapters.Adapters.StudentListAdapter;
import com.example.guest.adapters.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.MyViewHolder> {
    private final String TEXT_VIEW = "textView", IMAGE_VIEW = "imageView", VIDEO_VIEW = "videoView";

    private Context context;
    private List<String> names;
    private List<Date> dates;
    private List<Drawable> userPics;
    private List contentToShow;
    private List<String> viewToAdd;
    private static LayoutInflater inflater = null;

    public StudentRecyclerViewAdapter(Context context, List<String> names, List<Date> dates
            , List<Drawable> userPics, List contentToShow, List<String> viewToAdd) {
        this.context = context;
        this.names = names;
        this.dates = dates;
        this.userPics = userPics;
        this.contentToShow = contentToShow;
        this.viewToAdd = viewToAdd;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.students_adapter, parent, false);//TODO ???
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        View v = inflater.inflate(R.layout.students_adapter, null);
        ViewGroup vg = (ViewGroup)v.findViewById(R.id.llListItem);

        holder.tvName.setText(names.get(position));
        holder.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(dates.get(position)));
        holder.imageView.setImageDrawable(userPics.get(position));
        holder.imageButton.setImageResource(R.drawable.ic_person);

        holder.editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "wooooooow!!!!", Toast.LENGTH_LONG).show();
            }
        });

        addView(vg, holder, viewToAdd, position);
    }

    @Override
    public int getItemCount() {
        return contentToShow.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, ivToAdd;
        ImageButton imageButton;
        EditText editText;
        VideoView vvToAdd;
        TextView tvName, tvDate, tvLike, tvComment, tvShare, tvToAdd;

        public MyViewHolder(View v) {
            super(v);

            imageView = (ImageView)v.findViewById(R.id.ivUserPic);
            imageButton = (ImageButton)v.findViewById(R.id.imageButton);
            editText = (EditText)v.findViewById(R.id.editText);
            tvName = (TextView)v.findViewById(R.id.studentsListName);
            tvDate = (TextView)v.findViewById(R.id.studentsListDate);
            tvLike = (TextView)v.findViewById(R.id.tvLike);
            tvComment = (TextView)v.findViewById(R.id.tvComment);
            tvShare = (TextView)v.findViewById(R.id.tvShare);
        }
    }

    public void addView(ViewGroup vg, MyViewHolder holder, List<String> viewToAdd, int position){
        switch (viewToAdd.get(position)) {
            case TEXT_VIEW:
                holder.tvToAdd = (TextView)vg.findViewById(R.id.tvToAdd);
                holder.tvToAdd.setVisibility(View.VISIBLE);
                holder.tvToAdd.setText(contentToShow.get(position).toString());
                break;

            case IMAGE_VIEW:
                holder.ivToAdd = (ImageView)vg.findViewById(R.id.ivToAdd);
                holder.ivToAdd.setVisibility(View.VISIBLE);
                holder.ivToAdd.setImageURI(Uri.parse(contentToShow.get(position).toString()));
                break;

            case VIDEO_VIEW:
                holder.vvToAdd = (VideoView)vg.findViewById(R.id.vvToAdd);
                holder.vvToAdd.setVisibility(View.VISIBLE);
                holder.vvToAdd.setVideoURI(Uri.parse(contentToShow.get(position).toString()));
                holder.vvToAdd.start();
                Log.e("tag3",holder.vvToAdd.isShown()+"");
        }
    }
}
