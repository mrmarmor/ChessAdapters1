package com.example.guest.adapters.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import com.example.guest.adapters.R;

public class ChessGridFragment extends Fragment {
    String color;
    int columnFrom, rowFrom, columnTo, rowTo;
    private boolean isClickOnPiece;

    ImageButton[][] buttons = new ImageButton[8][8];

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chess_grid, container, false);

        GridLayout gridLayout = (GridLayout)v.findViewById(R.id.activity_main);
        gridLayout.setColumnCount(8);
        gridLayout.setRowCount(8);

        isClickOnPiece = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new ImageButton(getActivity());
                buttons[i][j].setLayoutParams(new GridView.LayoutParams(90, 90));
                buttons[i][j].setId(i+j*i);
                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default_small);

                if (i < 2) {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.white_rectangle));
                    buttons[i][j].setTag("white" + i + j);
                } else if (i > 5) {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.black_rectangle));
                    buttons[i][j].setTag("black" + i + j);
                } else {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.gray_rectangle));
                    buttons[i][j].setTag("empty" + i + j);
                }

                gridLayout.addView(buttons[i][j]);

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.e("tag1", v.getTag()+"");
                        click(v);
                    }
                });
                //Log.e("tag111", buttons[i][j].getTag()+"");
            }
        }

        return v;
    }

    //@Override
    public void click(View v) {
        String tag = v.getTag().toString();
        Log.e("tag2Grid", tag.substring(0,5)+":"+isClickOnPiece);

        if (isClickOnPiece && !tag.substring(0, 5).equals("empty")) {
            color = tag.substring(0, 5);
            columnFrom = Integer.parseInt(tag.substring(5, 6));
            rowFrom = Integer.parseInt(tag.substring(6, 7));
            isClickOnPiece = !isClickOnPiece;
            Log.e("tag22Grid", tag.substring(0,5));
        } else if (!isClickOnPiece && tag.substring(0, 5).equals("empty")) {
            columnTo = Integer.parseInt(tag.substring(5, 6));
            rowTo = Integer.parseInt(tag.substring(6, 7));
            Log.e("tag222Grid", color+":"+columnFrom+":"+rowFrom+":"+columnTo+":"+rowTo);
            v.setTag(color + columnTo + rowTo);
            buttons[columnFrom][rowFrom].setTag("empty" + columnFrom + rowFrom);
            isClickOnPiece = !isClickOnPiece;
            setBoard();
        }
        //Log.e("tag11111", v.getTag().toString());

    }

    public void setBoard() {
        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Log.e("tag111", buttons[i][j].getTag()+"");

                switch (buttons[i][j].getTag().toString().substring(0, 5)) {
                    case "white":
                        buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.white_rectangle));
                        break;
                    case "black":
                        buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.black_rectangle));
                        break;
                    case "empty":
                        buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.gray_rectangle));
                }
            }
            //Log.e("tag11", ((Button)gridView[i].getAdapter().getItem(i)).getTag()+"");
        }*/
        switch (color) {
            case "white":
                buttons[columnTo][rowTo].setImageDrawable(getResources()
                        .getDrawable(R.drawable.white_rectangle));
                break;
            case "black":
                buttons[columnTo][rowTo].setImageDrawable(getResources()
                        .getDrawable(R.drawable.black_rectangle));
        }
        buttons[columnFrom][rowFrom].setImageDrawable(getResources().getDrawable(
                R.drawable.gray_rectangle));
        Log.e("tag4Grid", columnFrom*8 + rowFrom+":"+columnTo*8 + rowTo);
    }

public class GridAdapter extends BaseAdapter {

        int[] images = {
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
                android.R.drawable.btn_minus, android.R.drawable.btn_minus
            /*    R.drawable.ctv_unchecked, R.drawable.ctv_unchecked,
                R.drawable.ctv_unchecked, R.drawable.ctv_unchecked,
                R.drawable.ctv_unchecked, R.drawable.ctv_unchecked,
                R.drawable.ctv_unchecked, R.drawable.ctv_unchecked*/
        };
        private Button button;
        private Context context;

        public GridAdapter(Context applicationContext, Button b){
            context = applicationContext;
            button = b;
        }

        @Override
        public int getCount() {
            //Number of data elements to be displayed
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return buttons[position][position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView != null){
                button = (Button)convertView;
            }
            else{
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(45, 45));
                //iv.setScaleType(ScaleType.CENTER);
                //button.setPadding(0, 0, 0, 0);
            }
            button.setBackground(getResources().getDrawable(images[position]));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag11", button.getTag() + "");
                }});
            return button;
        }

      /*  findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(new ChessGridFragment(), "tag1")
                .commit();
            }
        });*/


}
    //@Override
    public void finishUpdate(ViewGroup container){
        try {
            finishUpdate(container);
        }catch (NullPointerException e){

        }
    }
}

