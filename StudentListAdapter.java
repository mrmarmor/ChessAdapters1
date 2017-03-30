package com.example.guest.adapters.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.guest.adapters.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Guest on 26/03/2017.
 */

public class StudentListAdapter<T> extends BaseAdapter {
    private final String TEXT_VIEW = "textView", IMAGE_VIEW = "imageView", VIDEO_VIEW = "videoView";
    final String FILE_NAME = "giphy2.gif";

    private Context context;
    private List<String> names;
    private List<Date> dates;
    private List</*Uri*/Drawable> userPics;
    //private List<String> textToShow;
    private List<T> contentToShow;
    //private List<String> contentToShow;
    //private List<Integer> imageToShow;
    //private T<Uri> videoToShow;
    private List<String> viewToAdd;
    private static LayoutInflater inflater = null;

    //public StudentListAdapter(){}
    public StudentListAdapter(Context mContext, List<String> names
            , List<Date> dates, List</*Uri*/Drawable> userPics, List<T> contentToShow, List<String> view) {
        this.context = mContext;
        this.names = names;
        this.dates = dates;
        this.userPics = userPics;
        this.contentToShow = contentToShow;
        //this.textToShow = strings;
        //imageToShow = images;
        //videoToShow = videos;
        this.viewToAdd = view;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
/*    public <T> StudentListAdapter(Context mContext, List<String> names
            , List<String> dates, List<T> images, String view) {
        context = mContext;
        //textToShow = strings;
        this.names = names;
        this.dates = dates;
        imageToShow = images;
        //videoToShow = videos;
        viewToAdd = view;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/
    /*public StudentListAdapter(Context mContext, List<String> names
            , List<String> dates, List<Uri> videos, String view) {
        context = mContext;
        //textToShow = strings;
        //imageToShow = images;
        this.names = names;
        this.dates = dates;
        videoToShow = videos;
        viewToAdd = view;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/
    @Override
    public int getCount() { return contentToShow.size(); }

    @Override
    public T getItem(int position) {
        if (position >=0)
            return contentToShow.get(position);
        else
            return null;
    }
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Holder holder = new Holder();
        v = inflater.inflate(R.layout.students_adapter, null);
        ViewGroup vg = (ViewGroup)v.findViewById(R.id.llListItem);
        holder.imageView = (ImageView)vg.findViewById(R.id.ivUserPic);
        holder.imageButton = (ImageButton)vg.findViewById(R.id.imageButton);
        holder.editText = (EditText)vg.findViewById(R.id.editText);
        holder.tvName = (TextView)vg.findViewById(R.id.studentsListName);
        holder.tvDate = (TextView)vg.findViewById(R.id.studentsListDate);
        holder.tvLike = (TextView)vg.findViewById(R.id.tvLike);
        holder.tvComment = (TextView)vg.findViewById(R.id.tvComment);
        holder.tvShare = (TextView)vg.findViewById(R.id.tvShare);

        holder.tvName.setText(names.get(position));
        holder.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(dates.get(position)));
        //holder.ivToAdd.setImageURI(Uri.parse(contentToShow.get(position).toString()));
        holder.imageView.setImageDrawable(userPics.get(position));
        holder.imageButton.setImageResource(R.drawable.ic_person);

        addView(vg, holder, viewToAdd, position);

        return vg;
    }

    public void addView(ViewGroup parent, Holder holder, List<String> viewToAdd, int position){
        switch (viewToAdd.get(position)) {
            case TEXT_VIEW:
                holder.tvToAdd = (TextView)parent.findViewById(R.id.tvToAdd);
                holder.tvToAdd.setVisibility(View.VISIBLE);
                holder.tvToAdd.setText(contentToShow.get(position).toString());
                break;
            case IMAGE_VIEW:
                holder.ivToAdd = (ImageView)parent.findViewById(R.id.ivToAdd);
                holder.ivToAdd.setVisibility(View.VISIBLE);
                holder.ivToAdd.setImageURI(Uri.parse(contentToShow.get(position).toString()));

                //Resources res = context.getResources();
                //ImageView iv = new ImageView(context);
                //Job.Type[] type = Job.Type.values();

                //Drawable drawable = res.getDrawable(type[position].getBitmap());//imageToShow.get(position));
                //int color = res.getColor(type[position].getColor());//job.getType().getColor());
                //Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                //Drawable d = new BitmapDrawable(res, Bitmap.createScaledBitmap(bitmap, 60, 60, true));
                //d.setColorFilter(color, PorterDuff.Mode.MULTIPLY);

                //holder.tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, d, null);

                //parent.addView(holder.ivToAdd, 2);

                break;
            case VIDEO_VIEW:
                holder.vvToAdd = (VideoView)parent.findViewById(R.id.vvToAdd);
                holder.vvToAdd.setVisibility(View.VISIBLE);
            /*    try {
                    InputStream is = context.getAssets().open(FILE_NAME, context.MODE_PRIVATE);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //holder.vvToAdd.setBackgroundColor(Color.TRANSPARENT); //for gif without background
                //holder.vvToAdd.loadUrl("file:///android_asset/htmls/name.html");
                //parent.addView(holder.vvToAdd);
                //Picasso.with(context).load(contentToShow.get(position).toString())
                //       .into(holder.vvToAdd);
                holder.vvToAdd.setVideoURI(Uri.parse(contentToShow.get(position).toString()));
                holder.vvToAdd.start();
                Log.e("tag3",holder.vvToAdd.isShown()+"");

        }
    }

    public class Holder{
        ImageView imageView, ivToAdd;
        ImageButton imageButton;
        EditText editText;
        VideoView/*WebView*/ vvToAdd;
        TextView tvName, tvDate, tvLike, tvComment, tvShare, tvToAdd;
    }
}
