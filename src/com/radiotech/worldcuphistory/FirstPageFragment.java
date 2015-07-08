package com.radiotech.worldcuphistory;

import com.radiotech.worldcuphistory.WorldCupFragment.MyPagerAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstPageFragment extends Fragment {
	FragmentPagerAdapter adapterViewPager;

	public FirstPageFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_first_page, container,
				false);
		
		
		((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("تاریخچه فوتبال");
		((ActionBarActivity)getActivity()).getSupportActionBar().setSubtitle("تاریخچه و رکورد ها");
		
		
		PagerTabStrip pgt = (PagerTabStrip) v
				.findViewById(R.id.fist_pager_header);
		pgt.setTabIndicatorColor(Color.parseColor("#5cbce9"));
		ViewPager vpPager = (ViewPager) v.findViewById(R.id.first_vpPager);
		adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		return v;
	}

	public static class MyPagerAdapter extends FragmentPagerAdapter {
		private static int NUM_ITEMS = 2;

		public MyPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		// Returns total number of pages
		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		// Returns the fragment to display for that page
		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0: // Fragment # 0 - This will show FirstFragment
				return new HistoryFragment();
			case 1: // Fragment # 0 - This will show FirstFragment different
					// title
				return new RecordsFragment();
			default:
				return null;
			}
		}

		// Returns the page title for the top indicator
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "تاریخچه فوتبال";

			case 1:
				return "رکورد های جام جهانی";

			default:
				return "";

			}
		}

	}
}
