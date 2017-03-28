package com.example.moshe.chessadapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity /*implements Button.OnClickListener*/{
    String color;
    int columnFrom, rowFrom, columnTo, rowTo;
    boolean isClickOnPiece = true;

    ImageButton[][] buttons = new ImageButton[8][8];
    GridView[] gridView = new GridView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.activity_main);
        gridLayout.setColumnCount(8);
        gridLayout.setRowCount(8);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new ImageButton(getApplicationContext()/*this*/);
                //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(90, 90);

                //View view = inflater.inflate(R.layout.square, container, false);
                //buttons[i][j] = (Button)findViewById(R.id.squareButton1); //new Button(getActivity());
                //buttons[i][j].getLayoutParams().width = 100;
                //buttons[i][j].getLayoutParams().height = 140;
                buttons[i][j].setLayoutParams(new GridView.LayoutParams(90, 90));
                //params.setMargins(5,5,5,5);
                //buttons[i][j].setLayoutParams(params);
                //buttons[i][j].setLayoutParams(ll);
                buttons[i][j].setId(i+j*i);
                buttons[i][j].setBackgroundResource(
                        android.R.drawable.btn_default_small);
                //buttons[i][j].getLayoutParams().width = 100;
                //buttons[i][j].getLayoutParams().height = 140;
                //buttons[i][j].requestLayout();

                if (i < 2) {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.white_rectangle));                    buttons[i][j].setTag("white" + i + j);
                } else if (i > 5) {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.black_rectangle));
                    buttons[i][j].setTag("black" + i + j);
                } else {
                    buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.gray_rectangle));                    buttons[i][j].setTag("empty" + i + j);
                }
                //gridView[i] = (GridView)gridLayout.getChildAt(i);//findViewByTag("gvChess"+i);

                gridLayout.addView(buttons[i][j]);
                //gridView[i].setAdapter(new GridAdapter(this, buttons[i][j]));
                //gridLayout.removeAllViewsInLayout();
                //gridView[i].addView(buttons[i][j]);

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("tag1", v.getTag()+"");
                        click(v);
                    }
                });
                Log.e("tag111", buttons[i][j].getTag()+"");

                //gridLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            //container.addView(gridLayout);

            //container.addView(getLayoutInflater(null).inflate(R.layout.fragment_chess_grid));
            //for (int index = 0; index < 8; index++) {
            //gridView[i] = (GridView) findViewById(R.id.gvChess);
            //gridView[i].set
            //gridView[i].setY(i * 100);//VerticalSpacing(i*100);
                //gridView[index].setNumColumns(8);
            //ArrayAdapter<Button> adapter = new ArrayAdapter<Button>(this,android.R.layout.simple_list_item_1, buttons);

            //gridView[i].setAdapter(new GridAdapter(this));
            //Log.e("tag1", ((Button) gridView[i].getAdapter().getItem(i)).getTag() + "");
            //gridView[i].setAdapter(new ArrayAdapter<Button>(this, R.layout.square, buttons[7]));//new SquareAdapter(getActivity()));
            //}
        }


        //getActivity().setContentView(gridView);
        //v = gridView;
        //container.addView(gridLayout);
        //View inflatedView = View.inflate(getActivity(), R.layout.fragment_chess_grid, gridLayout);

        //return v;
    }

    //@Override
    public void click(View v) {
        String tag = v.getTag().toString();
        Log.e("tag2", tag.substring(0,5)+":"+isClickOnPiece);

        if (isClickOnPiece && tag.substring(0, 5) != "empty") {
            color = tag.substring(0, 5);
            columnFrom = Integer.parseInt(tag.substring(5, 6));
            rowFrom = Integer.parseInt(tag.substring(6, 7));
            Log.e("tag22", tag.substring(0,3));

            //v.setTag(null);
            isClickOnPiece = !isClickOnPiece;
        } else if (!isClickOnPiece || tag.substring(0, 5) == "empty") {
            columnTo = Integer.parseInt(tag.substring(5, 6));
            rowTo = Integer.parseInt(tag.substring(6, 7));
            Log.e("tag222", columnFrom+":"+rowFrom);

            v.setTag(color + columnTo + rowTo);
            buttons[columnFrom][rowFrom].setTag("empty" + columnFrom + rowFrom);
            isClickOnPiece = !isClickOnPiece;
            setBoard();
        }
        Log.e("tag11111", v.getTag().toString());

    }

    public void setBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Log.e("tag111", buttons[i][j].getTag()+"");

                switch (buttons[i][j].getTag().toString().substring(0, 5)) {
                    case "white":
                        buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.white_rectangle));                        break;
                    case "black":
                        buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.black_rectangle));                        break;
                    case "empty":
                        buttons[i][j].setImageDrawable(getResources().getDrawable(R.drawable.gray_rectangle));                }
                //buttons[i][j].setTag("white" + i + j);
            }
            //Log.e("tag11", ((Button)gridView[i].getAdapter().getItem(i)).getTag()+"");

            //gridView[i].setAdapter(new GridAdapter(this, buttons[i][j]));
        }
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
            context=applicationContext;
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
