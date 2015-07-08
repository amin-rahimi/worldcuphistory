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

public class BrazilWorldCupFragment extends Fragment {

	FragmentPagerAdapter adapterViewPager;
	WorldCupItem worldcup;
	public static WorldCupItem witem;
	public static final String WORLD_CUP_ITEM = "world_cup_item";
	
	public BrazilWorldCupFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		worldcup = (WorldCupItem) getArguments()
				.getSerializable(WORLD_CUP_ITEM);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_brazil_worldcup, container,
				false);
		
		witem = worldcup;
		setTitle("جام جهانی" + " " + String.valueOf(worldcup.getYear()));
		((WorldCupActivity) getActivity()).getSupportActionBar().setSubtitle(
				getResources().getString(worldcup.getCountryNameRes()));
		
		PagerTabStrip pgt = (PagerTabStrip) v.findViewById(R.id.brazil_pager_header);
		pgt.setTabIndicatorColor(Color.parseColor("#5cbce9"));
		ViewPager vpPager = (ViewPager) v.findViewById(R.id.brazil_vpPager);
		adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
		vpPager.setAdapter(adapterViewPager);
		return v;
	}

	public static class MyPagerAdapter extends FragmentPagerAdapter {
		private static int NUM_ITEMS = 10;

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
				return DescriptionFragment.newInstance(witem);
			case 1: // Fragment # 0 - This will show FirstFragment different
					// title
				return new BrazilGroupsFragment().newIntance(1);
			case 2: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(2);
			case 3: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(3);
			case 4: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(4);
			case 5: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(5);
			case 6: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(6);
			case 7: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(7);
			case 8: // Fragment # 1 - This will show SecondFragment
				return new BrazilGroupsFragment().newIntance(8);
			case 9: // Fragment # 1 - This will show SecondFragment
				return PicturesFragment.newInstance(witem);
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
				return "گروه A";
			case 2:
				return "گروه B";
			case 3:
				return "گروه C";
			case 4:
				return "گروه D";
			case 5:
				return "گروه E";
			case 6:
				return "گروه F";
			case 7:
				return "گروه G";
			case 8:
				return "گروه H";

			
			case 9:
				return "عکس ها";
			default:
				return "";

			}
		}

	}
	
	public void setTitle(CharSequence title) {
		((WorldCupActivity) getActivity()).getSupportActionBar()
				.setTitle(title);
	}


}
