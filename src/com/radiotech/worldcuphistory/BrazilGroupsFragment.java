package com.radiotech.worldcuphistory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class BrazilGroupsFragment extends Fragment {

	public final static String GROUP_NUMBER = "group_number";
	private int mGroupNumber;
	private ArrayList<String> mGroupInfo;
	private HashMap<String, Integer> mFlags;
	private HashMap<String, String> mDict;

	public BrazilGroupsFragment newIntance(int groupNumber) {
		BrazilGroupsFragment fragment = new BrazilGroupsFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(GROUP_NUMBER, groupNumber);
		fragment.setArguments(bundle);
		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mGroupNumber = getArguments().getInt(GROUP_NUMBER);
		mGroupInfo = getGroupData(mGroupNumber);
		mFlags = loadFlags();
		mDict = loadCountryDic();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_brazil_groups, container,
				false);
		ArrayList<TableRow> tables = new ArrayList<TableRow>();
		TableRow tb1 = (TableRow) v.findViewById(R.id.tableRowBrazil1);
		TableRow tb2 = (TableRow) v.findViewById(R.id.tableRowBrazil2);
		TableRow tb3 = (TableRow) v.findViewById(R.id.tableRowBrazil3);
		TableRow tb4 = (TableRow) v.findViewById(R.id.tableRowBrazil4);
		tb1.setBackgroundColor(Color.parseColor("#f2f2f2"));
		tb2.setBackgroundColor(Color.parseColor("#f2f2f2"));
		tb3.setBackgroundColor(Color.parseColor("#f2f2f2"));
		tb4.setBackgroundColor(Color.parseColor("#f2f2f2"));
		tables.add(tb1);
		tables.add(tb2);
		tables.add(tb3);
		tables.add(tb4);

		for (int i = 0; i < 4; i++) {

			ImageView imageView = (ImageView) tables.get(i).getChildAt(1);
			TextView textView = (TextView) tables.get(i).getChildAt(0);
			if (mDict.containsKey(mGroupInfo.get(i))) {
				textView.setText(mDict.get(mGroupInfo.get(i)));
			} else {
				textView.setText("");
			}

			if (mFlags.containsKey(mGroupInfo.get(i))) {
				imageView.setImageResource(mFlags.get(mGroupInfo.get(i)));
			}

		}

		TextView times = (TextView) v.findViewById(R.id.textViewTimes);

		String s = "";
		for (int i = 4; i < mGroupInfo.size(); i++) {
			s += mGroupInfo.get(i) + "\n";
		}
		times.setText(s);
		return v;
	}

	public ArrayList<String> getGroupData(int groupNumber) {
		ArrayList<String> array = new ArrayList<String>();
		int counter = 0;
		try {
			InputStream stream = getActivity().getAssets().open(
					"2014/TXT/4-Groups Results.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));

			for (;;) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				if (line.compareTo("") == 0 || line.compareTo(" ") == 0
						|| line.compareTo("  ") == 0) {
					continue;
				}

				if (counter > groupNumber) {
					break;
				}
				if (line.compareTo("#") == 0) {
					counter++;
					continue;
				}

				if (counter == groupNumber) {
					array.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;

	}

	public HashMap<String, Integer> loadFlags() {
		HashMap<String, Integer> flags = new HashMap<String, Integer>();
		ArrayList<Integer> flagsDrawable = new ArrayList<Integer>();
		flagsDrawable.add(R.drawable.brazil);
		flagsDrawable.add(R.drawable.germany);
		flagsDrawable.add(R.drawable.italy);
		flagsDrawable.add(R.drawable.argentina);
		flagsDrawable.add(R.drawable.mexico);
		flagsDrawable.add(R.drawable.united_kingdom);
		flagsDrawable.add(R.drawable.spain);
		flagsDrawable.add(R.drawable.france);
		flagsDrawable.add(R.drawable.yugoslavia);
		flagsDrawable.add(R.drawable.belgium);
		flagsDrawable.add(R.drawable.sweden);
		flagsDrawable.add(R.drawable.uruguay);
		flagsDrawable.add(R.drawable.russia);
		flagsDrawable.add(R.drawable.united_states);
		flagsDrawable.add(R.drawable.switzerland);
		flagsDrawable.add(R.drawable.netherlands);
		flagsDrawable.add(R.drawable.hungary);
		flagsDrawable.add(R.drawable.czech_republic);
		flagsDrawable.add(R.drawable.scotland);
		flagsDrawable.add(R.drawable.paraguay);
		flagsDrawable.add(R.drawable.south_korea);
		flagsDrawable.add(R.drawable.chile);
		flagsDrawable.add(R.drawable.romania);
		flagsDrawable.add(R.drawable.poland);
		flagsDrawable.add(R.drawable.bulgaria);
		flagsDrawable.add(R.drawable.austria);
		flagsDrawable.add(R.drawable.cameroon);
		flagsDrawable.add(R.drawable.portugal);
		flagsDrawable.add(R.drawable.peru);
		flagsDrawable.add(R.drawable.nigeria);
		flagsDrawable.add(R.drawable.tunisia);
		flagsDrawable.add(R.drawable.saudi_arabia);
		flagsDrawable.add(R.drawable.morocco);
		flagsDrawable.add(R.drawable.japan);
		flagsDrawable.add(R.drawable.denmark);
		flagsDrawable.add(R.drawable.colombia);
		flagsDrawable.add(R.drawable.ireland);
		flagsDrawable.add(R.drawable.norway);
		flagsDrawable.add(R.drawable.ireland);
		flagsDrawable.add(R.drawable.iran);
		flagsDrawable.add(R.drawable.croatia);
		flagsDrawable.add(R.drawable.costa_rica);
		flagsDrawable.add(R.drawable.south_africa);
		flagsDrawable.add(R.drawable.algeria);
		flagsDrawable.add(R.drawable.australia);
		flagsDrawable.add(R.drawable.greece);
		flagsDrawable.add(R.drawable.ghana);
		flagsDrawable.add(R.drawable.el_salvador);
		flagsDrawable.add(R.drawable.egypt);
		flagsDrawable.add(R.drawable.ecuador);
		flagsDrawable.add(R.drawable.ivort_couast);
		flagsDrawable.add(R.drawable.slovenia);
		flagsDrawable.add(R.drawable.new_zealand);
		flagsDrawable.add(R.drawable.honduras);
		flagsDrawable.add(R.drawable.north_korea);
		flagsDrawable.add(R.drawable.turkey);
		flagsDrawable.add(R.drawable.iraq);
		flagsDrawable.add(R.drawable.indonesia);
		flagsDrawable.add(R.drawable.haiti);
		flagsDrawable.add(R.drawable.united_arab_emirates);
		flagsDrawable.add(R.drawable.ukraine);
		flagsDrawable.add(R.drawable.trinidad_and_tobago);
		flagsDrawable.add(R.drawable.germany);
		flagsDrawable.add(R.drawable.republic_of_the_congo);
		flagsDrawable.add(R.drawable.china);
		flagsDrawable.add(R.drawable.canada);
		flagsDrawable.add(R.drawable.angola);
		flagsDrawable.add(R.drawable.wales);
		flagsDrawable.add(R.drawable.togo);
		flagsDrawable.add(R.drawable.senegal);
		flagsDrawable.add(R.drawable.kuwait);
		flagsDrawable.add(R.drawable.jamaica);
		flagsDrawable.add(R.drawable.israel);
		flagsDrawable.add(R.drawable.germany);
		flagsDrawable.add(R.drawable.russia);
		flagsDrawable.add(R.drawable.czech_republic);
		flagsDrawable.add(R.drawable.serbia);
		flagsDrawable.add(R.drawable.serbia);
		flagsDrawable.add(R.drawable.india);
		flagsDrawable.add(R.drawable.india);
		flagsDrawable.add(0);
		flagsDrawable.add(R.drawable.zaire);
		flagsDrawable.add(R.drawable.bolivia);
		flagsDrawable.add(R.drawable.slovakia);
		flagsDrawable.add(R.drawable.cuba);
		flagsDrawable.add(R.drawable.bosnia_and_herzegovina);

		try {

			InputStream stream = getActivity().getAssets().open("dic.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));

			int i = 0;
			for (;;) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				if (line.compareTo("") == 0) {
					continue;
				}
				String[] lineSplit = line.split("#");

				if (lineSplit.length > 1) {
					flags.put(lineSplit[2], flagsDrawable.get(i));
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flags;
	}

	public HashMap<String, String> loadCountryDic() {
		HashMap<String, String> countryDic = new HashMap<String, String>();
		try {

			InputStream stream = getActivity().getAssets().open("dic.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));

			for (;;) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				if (line.compareTo("") == 0) {
					continue;
				}
				String[] lineSplit = line.split("#");

				if (lineSplit.length > 1) {
					countryDic.put(lineSplit[2], lineSplit[1]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryDic;

	}

}
