package com.example.guest.adapters.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guest.adapters.R;

import java.util.ArrayList;

import static android.R.attr.button;
import static android.R.attr.drawable;

public class ChessRecyclerFragment extends Fragment {

    private String color;
    private int columnFrom, rowFrom, columnTo, rowTo;
    private boolean isClickOnPiece = true;

    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;
    ImageButton[][] buttons = new ImageButton[8][8];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chess_recycler, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rvColumn);

        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new ImageButton(getActivity());
                //buttons[i][j].setLayoutParams(new GridView.LayoutParams(40, 40));
                //buttons[i][j].setId(i);
                //buttons[i][j].setBackgroundResource(android.R.drawable.btn_minus);

                if (j < 2) {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.black_rectangle));
                    buttons[i][j].setTag("black" + i + j);
                } else if (j > 5) {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.white_rectangle));
                    buttons[i][j].setTag("white" + i + j);
                } else {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.gray_rectangle));
                    buttons[i][j].setTag("empty" + i + j);
                }
                Log.e("tag1", buttons[i][j].getTag() + "");
               *//* buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("tag1", v.getTag() + "");
                        click(v);
                    }
                });
               *//* //Log.e("tag3", buttons[i][j].getTag() + "");
            }
        }*/

        StaggeredGridLayoutManager sglManager = new StaggeredGridLayoutManager(8, 1);
        recyclerView.setLayoutManager(sglManager);//new GridLayoutManager(this, 8, GridLayoutManager.VERTICAL, false));
        rvAdapter = new RecyclerViewAdapter(getActivity(), getFragmentManager(), buttons);
        recyclerView.setAdapter(rvAdapter);
        //rvAdapter.notifyDataSetChanged();


        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 8);
       /* recyclerView.setLayoutManager(new GridLayoutManager(this, 8, GridLayoutManager.VERTICAL, false));
        //for (int i = 0; i < 8; i++) {
            rvAdapter = new RecyclerViewAdapter(this, recyclerView, getSupportFragmentManager(), buttons);
            //recyclerView.addView(buttons[1][1]);
            recyclerView.setAdapter(rvAdapter);
            rvAdapter.notifyDataSetChanged();
        *///}
        return v;
    }

    public void click(ViewGroup vg, View v) {
        String tag = v.getTag().toString();
        Log.e("tag2", tag.substring(0,5)+":"+isClickOnPiece);

        if (isClickOnPiece && tag.substring(0, 5) != "empty") {
            color = tag.substring(0, 5);
            columnFrom = Integer.parseInt(tag.substring(5, 6));
            rowFrom = Integer.parseInt(tag.substring(6, 7));
            //Log.e("tag22", tag.substring(0,3)+":"+columnFrom+rowFrom);

            v.setTag("empty" + columnFrom + rowFrom);
            ((ImageButton)v).setImageDrawable(getResources().getDrawable(
                    R.drawable.gray_rectangle));
            isClickOnPiece = !isClickOnPiece;
        } else if (!isClickOnPiece || tag.substring(0, 5) == "empty") {
            columnTo = Integer.parseInt(tag.substring(5, 6));
            rowTo = Integer.parseInt(tag.substring(6, 7));
            //Log.e("tag222", color+":"+columnFrom+":"+rowFrom+":"+columnTo+":"+rowTo);

            v.setTag(color + columnTo + rowTo);

            //buttons[columnFrom][rowFrom].setTag("empty" + columnFrom + rowFrom);
            isClickOnPiece = !isClickOnPiece;
            setBoard((ImageButton)v);
        }
        //Log.e("tag11111", v.getTag().toString());

    }

    public void setBoard(ImageButton ib/*String color, int columnFrom, int rowFrom, int columnTo, int rowTo*/) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Log.e("tag111", buttons[i][j].getTag()+"");
                switch (ib.getTag().toString().substring(0, 5)) {
                    case "white":
                        ib.setImageDrawable(getResources().getDrawable(R.drawable.white_rectangle));
                        break;
                    case "black":
                        ib.setImageDrawable(getResources().getDrawable(R.drawable.black_rectangle));
                        break;
                    case "empty":
                        ib.setImageDrawable(getResources().getDrawable(R.drawable.gray_rectangle));                }
                //buttons[i][j].setTag("white" + i + j);
            }
            //Log.e("tag11", ((Button)gridView[i].getAdapter().getItem(i)).getTag()+"");
            //gridView[i].setAdapter(new GridAdapter(this, buttons[i][j]));
        }
        //rvAdapter = new RecyclerViewAdapter(this, getSupportFragmentManager(), buttons);
        //recyclerView.setAdapter(rvAdapter);
        //buttons[columnFrom][rowFrom];
        /*switch (color){
            case "white":
                ib*//*buttons[columnTo][rowTo]*//*.setImageDrawable(getResources()
                        .getDrawable(R.drawable.white_rectangle));
                break;
            case "black":
                ib*//*buttons[columnTo][rowTo]*//*.setImageDrawable(getResources()
                        .getDrawable(R.drawable.black_rectangle));
                break;
            default:
                ib*//*buttons[columnTo][rowTo]*//*.setImageDrawable(getResources()
                        .getDrawable(R.drawable.gray_rectangle));
        }
        Log.e("tag4", vg+":" + vg.getChildAt(columnFrom*8 + rowFrom)+":"+columnFrom*8 + rowFrom+":" + columnFrom+":"+rowFrom*//*+ vg.getChildAt(columnFrom*8 + rowFrom).getTag()*//*);
        //int s = (int)columnFrom*8;
        //int ss = (int)rowFrom;
        int sss = columnFrom*8 + rowFrom;
        int ssss = columnTo*8 + rowTo;
        Log.e("tag44", sss+"");
       *//* ((ImageButton)vg.getChildAt(sss)).
        *//**//*buttons[columnFrom][rowFrom].*//**//*setImageDrawable(getResources().getDrawable(
                R.drawable.gray_rectangle));*//*

        Log.e("tag5", (columnFrom*8 + rowFrom)+"");
        //rvAdapter.notifyItemChanged(sss);
        rvAdapter.notifyItemChanged(ssss);
        Log.e("tag44", columnFrom+":" + rowFrom+":-:"+columnTo+":" + rowTo);*/
    }



    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private ImageButton[][] buttons;
        int ii = 0;
        private Context context;
        FragmentManager fragmentManager;
        private Resources res;
        //int position;

        public RecyclerViewAdapter(Context context/*, RecyclerView mRecyclerView*/
                , FragmentManager fragmentManager, ImageButton[][] buttons/*, ArrayList<Job> jobs*/) {
            this.context = context;
            //this.mRecyclerView = mRecyclerView;
            this.fragmentManager = fragmentManager;
            this.buttons = buttons;
            res = context.getResources();
            //this.position = position;
            //this.jobs = jobs;
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            //ViewPager vp;
            //RecyclerView recyclerView;
            //private ImageButton[][] buttons = new ImageButton[8][8];
            private ImageButton imageButton;

            public ViewHolder(View v) {
                super(v);

                imageButton = (ImageButton) v.findViewById(R.id.ib);
                imageButton.setOnClickListener(this);

                //Log.e("tag3", button + ":" + ":" + imageButton + ":" + ii);

                if (ii < 16) {
                    imageButton.setImageDrawable(res.getDrawable(R.drawable.black_rectangle));
                    imageButton.setTag("black" + ii / 8 + ii % 8);
                } else if (ii > 47) {
                    imageButton.setImageDrawable(res.getDrawable(R.drawable.white_rectangle));
                    imageButton.setTag("white" + ii / 8 + ii % 8);
                } else {
                    imageButton.setImageDrawable(res.getDrawable(R.drawable.gray_rectangle));
                    imageButton.setTag("empty" + ii / 8 + ii % 8);
                }
                //Log.e("tag3", imageButton.getTag() + "");
                ii++;
            }

            @Override
            public void onClick(View view) {
                click((ViewGroup)view.getParent(), view);
            }
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_button, null);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {}

        @Override
        public int getItemCount() {
            return 64;
        }
    }
}