package com.recipes.dewordy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.recipes.dewordy.fragment.CategoryFragment.TabFragment.TabFragment12;

/**
 * Created by Paulstanley on 3/5/16.
 */

    public class CustomPagerAdapter2 extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public CustomPagerAdapter2(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    TabFragment12 tab1 = new TabFragment12();
                    return tab1;
//                case 1:
//                    TabFragment2 tab2 = new TabFragment2();
//                    return tab2;
//                case 2:
//                    TabFragment3 tab3 = new TabFragment3();
//                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }


