package com.example.moshe.chessadapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChessGridFragment extends Fragment implements Button.OnClickListener {
    String color;
    int columnFrom, rowFrom, columnTo, rowTo;
    boolean isClickOnPiece = true;

    Button[][] buttons = new Button[8][8];
    GridView[] gridView = new GridView[8];

    public ChessGridFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chess_grid, container, false);

        //gridView.
        Log.e("tag2", container + "");
        //gridView.setAdapter(new SimpleAdapter(getActivity())//new SquareAdapter(getActivity()));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new Button(getActivity());
                //ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                View view = inflater.inflate(R.layout.square, container, false);
                buttons[i][j] = (Button) view.findViewById(R.id.squareButton); //new Button(getActivity());
                //buttons[i][j].getLayoutParams().width = 100;
                //buttons[i][j].getLayoutParams().height = 140;
                //buttons[i][j].setLayoutParams(ll);
                buttons[i][j].setId(i + j);
                buttons[i][j].requestLayout();

                if (i < 2) {
                    buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.white));
                    buttons[i][j].setTag("white" + i + j);
                } else if (i > 5) {
                    buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.black));
                    buttons[i][j].setTag("black" + i + j);
                } else {
                    buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    buttons[i][j].setTag(i + j);
                }

                //gridLayout.removeAllViewsInLayout();
                //gridView.addView(buttons[i][j]);
                //buttons[i][j].setOnClickListener(this);
                //gridLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            //container.addView(gridLayout);

            //container.addView(getLayoutInflater(null).inflate(R.layout.fragment_chess_grid));
            //for (int index = 0; index < 8; index++) {
                gridView[i] = (GridView) v.findViewById(R.id.gvChess);
                //gridView[index].setNumColumns(8);
            gridView[i].setAdapter(new GridAdapter(getActivity()));
            //gridView[i].setAdapter(new ArrayAdapter(getActivity(), R.layout.square, buttons));//new SquareAdapter(getActivity()));
            //}
        }


        //getActivity().setContentView(gridView);
        //v = gridView;
        //container.addView(gridLayout);
        //View inflatedView = View.inflate(getActivity(), R.layout.fragment_chess_grid, gridLayout);

        return v;
    }

    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        if (isClickOnPiece && tag.length() == 7) {
            color = tag.substring(0, 5);
            columnFrom = tag.charAt(5);
            rowFrom = tag.charAt(6);

            //v.setTag(null);
            isClickOnPiece = !isClickOnPiece;
        } else if (!isClickOnPiece && tag.length() == 2) {
            columnTo = tag.charAt(0);
            rowTo = tag.charAt(1);

            v.setTag(color + columnTo + rowTo);
            isClickOnPiece = !isClickOnPiece;
            setBoard();
        }

    }

    public void setBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (buttons[i][j].getTag().toString().substring(0, 2)) {
                    case "wh":
                        buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.white));
                        break;
                    case "bl":
                        buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.black));
                        break;
                    default:
                        buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
                //buttons[i][j].setTag("white" + i + j);
            }
        }
    }

    public class GridAdapter extends BaseAdapter {

        int[] images = {
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
                android.R.drawable.btn_minus, android.R.drawable.btn_minus,
        };
        private Context context;

        public GridAdapter(Context applicationContext){
            context=applicationContext;
        }

        @Override
        public int getCount() {
            //Number of data elements to be displayed
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if(convertView != null){
                button = (Button)convertView;
            }
            else{
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(20, 20));
                //iv.setScaleType(ScaleType.CENTER);
                //button.setPadding(0, 0, 0, 0);
            }
            button.setBackground(getResources().getDrawable(images[position]));
            return button;
        }

    }
   /* @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.getFragmentManager() != null && getFragmentManager().findFragmentById(new ChessGridFragment()))
    }*/

    /*public void create() {
            GridLayout layout1 = new GridLayout(getActivity());
            for(int i = 0; i < layout1.getChildCount(); i++) {
                if(grid[i] == 0) { //if grid pos. indicates an empty cell
                    Button empty = new Button(this);
                    empty.setBackgroundResource(R.drawable.emptybutton); //set background to empty
                    empty.setId(i); //set id to value of i
                    empty.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    layout1.addView(empty); //add the button to the relativeLayout view
                    //((Button) findViewById(i)).setOnClickListener(emptyListener);
                }
            }
            getActivity().setContentView(layout1);
        }*/
/*    public class SquareAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;
        //private Button button;

        public SquareAdapter(Context mContext) {
            this.mContext = mContext;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return
            return getFragmentManager().findFragmentById(R.id.chessGridFragment);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //View rowView = convertView;
            //v = inflater.inflate(R.layout.one_item, null);
            ViewGroup vg = (ViewGroup)convertView.findViewById(R.id.llSquare);

            //View rowView = mInflater.inflate(R.layout.square, null);
            ViewHolder holder = new ViewHolder();
            holder.square = (Button)vg*//*rowView*//*.findViewById(R.id.squareButton);
            holder.square.setOnClickListener(new ChessGridFragment());


            return vg;//rowView;
        }
    }

    public class ViewHolder {
        public Button square;
    }*/

}
