package com.radiotech.worldcuphistory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorldCupFragment extends Fragment {
	public static final String WORLD_CUP_ITEM = "world_cup_item";
	WorldCupItem worldcup;
	public static WorldCupItem witem;
	FragmentPagerAdapter adapterViewPager;

	public WorldCupFragment(){}
	
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
		View v = inflater
				.inflate(R.layout.fragment_world_cup, container, false);
		worldcup = (WorldCupItem) getArguments()
				.getSerializable(WORLD_CUP_ITEM);
		witem = worldcup;
		setTitle("جام جهانی" + " " + String.valueOf(worldcup.getYear()));
		((WorldCupActivity) getActivity()).getSupportActionBar().setSubtitle(
				getResources().getString(worldcup.getCountryNameRes()));
		
		PagerTabStrip pgt = (PagerTabStrip)v.findViewById(R.id.pager_header);
		pgt.setTabIndicatorColor(Color.parseColor("#5cbce9"));
		ViewPager vpPager = (ViewPager) v.findViewById(R.id.vpPager);
		adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		return v;

	}

	public void setTitle(CharSequence title) {
		((WorldCupActivity) getActivity()).getSupportActionBar()
				.setTitle(title);
	}

	public static class MyPagerAdapter extends FragmentPagerAdapter {
		private static int NUM_ITEMS = 4;
		

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
				return DescriptionFragment.newInstance(WorldCupFragment.witem);
			case 1: // Fragment # 0 - This will show FirstFragment different
					// title
				return GroupsFragment.newInstance(WorldCupFragment.witem);
			case 2: // Fragment # 1 - This will show SecondFragment
				return FinalStagesFragment.newInstance(WorldCupFragment.witem);
			case 3: // Fragment # 1 - This will show SecondFragment
				return PicturesFragment.newInstance(WorldCupFragment.witem);
			default:
				return null;
			}
		}

		// Returns the page title for the top indicator
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "توضیحات";

			case 1:
				return "مراحل گروهی";

			case 2:
				return "مراحل حذفی";
			case 3:
				return "عکس ها";
			default:
				return "";

			}
		}

	}

}
