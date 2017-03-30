package com.example.guest.adapters;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.guest.adapters.Fragments.ChessGridFragment;
import com.example.guest.adapters.Fragments.ChessRecyclerFragment;
import com.example.guest.adapters.Fragments.StudentsListFragment;
import com.example.guest.adapters.Fragments.StudentsNoAdapterFragment;
import com.example.guest.adapters.Fragments.StudentsRecyclerFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment[] fragments = new Fragment[]{new StudentsNoAdapterFragment(), new StudentsListFragment()
            , new StudentsRecyclerFragment(), new ChessGridFragment(), new ChessRecyclerFragment()};
  /*  private int[] buttonsResId = new int[]{R.id.button_students_no_adapter, R.id.button_students_adapter
        , R.id.button_students_recycler, R.id.button_chess_grid, R.id.buttons_chess_recycler};
*/
    private Button[] buttons = new Button[5];
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    //private FragmentTabHost mTabHost;
    //private TabWidget tw;
    //Button[] buttons = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout)findViewById(R.id.llTabs);
        for (int i = 1; i < 6; i++) {
            buttons[i - 1] = (Button) ll.findViewWithTag("btn" + i);
            buttons[i - 1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag0", v+":"+v.getTag()+":"+Integer.parseInt(v.getTag().toString().substring(3)));
                    mViewPager.setCurrentItem(Integer.parseInt(v.getTag().toString().substring(3)) - 1);
                }
            });
        }

        mViewPager = (ViewPager)findViewById(R.id.vp);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        /*mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realTabContent);

        tw = mTabHost.getTabWidget();
        mTabHost.addTab(mTabHost.newTabSpec("a").setIndicator("Students1"), StudentsNoAdapterFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("b").setIndicator("Students2"), StudentsListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("c").setIndicator("Students3"), StudentsListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("d").setIndicator("Chess1"), ChessGridFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("e").setIndicator("Chess2"), ChessRecyclerFragment.class, null);
        mTabHost.setCurrentTab(0);*/
        setVPHeaderAppearance();

        /*mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mViewPager.setCurrentItem(mTabHost.getCurrentTab());
                mTabHost.setCurrentTab(mTabHost.getCurrentTab());
                //mPagerAdapter.notifyDataSetChanged();
                setVPHeaderAppearance();
            }

        *//* for (int i = 0; i < buttonsResId.length; i++)
            buttons[i] = (Button)findViewById(buttonsResId[i]);*//*
            //Log.e("tag", mPagerAdapter.getPageTitle(mPagerAdapter.)+"");

        });*/

    }

    public void setVPHeaderAppearance() {
        //tw.setBackgroundColor(getResources().getColor(R.color.orange));
        //findViewById(R.id.llTabs).setBackgroundColor(getResources().getColor(R.color.orange));
        //tw.setAlpha(.5f);
        //tw.getChildAt(mTabHost.getCurrentTab()).setAlpha(.1f);
        for (int i = 0; i < 5/*mViewPager.getChildCount()*/; i++) {
            //Button v = (Button)buttons;
            buttons[i]/*tw.getChildAt(i)*/.setAlpha(.5f);
            if (i == mViewPager.getCurrentItem())//mTabHost.getCurrentTab())
                buttons[i]/*tw.getChildAt(i)*/.setAlpha(1);
            //v.setBackgroundColor(getResources().getColor(R.color.orange));
            //buttons[i].setAlpha(1f);
             /*   } else {
                    buttons[i].setAlpha(.5f);
                }*/
        }
    }

/*    private void addTab(String labelId, String s, Class<?> frg) {
        TabHost.TabSpec spec = mTabHost.newTabSpec(labelId);
        spec.setIndicator(", d);
        mTabHost.addTab(spec, frg, null);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {
        *//**
         * The fragment argument representing the section number for this
         * fragment.
         *//*
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        *//**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }*/

    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //findViewById(buttonsResId[position]).setAlpha(.5f);
            //setVPHeaderAppearance(position-1);
            //mPagerAdapter.notifyDataSetChanged();
            //mTabHost.setCurrentTab(position);
            Log.e("tag1", position+":"+ mViewPager.getCurrentItem());//  mTabHost.getCurrentTab());

            setVPHeaderAppearance();
            return fragments[position];//PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_UNCHANGED;
        }

     /*   @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }*/

   /*     @Override
        public CharSequence getPageTitle(int position) {
      *//*      switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }*//*
            return position+"";
        }*/

    }
}
