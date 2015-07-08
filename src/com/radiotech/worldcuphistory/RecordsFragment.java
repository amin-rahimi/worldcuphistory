package com.radiotech.worldcuphistory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class RecordsFragment extends Fragment {
	RecordsExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, ArrayList<String>> listDataChild;
	ArrayList<String> mTitles;
	ArrayList<ArrayList<String>> mTexts;

	public RecordsFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_records, container, false);

		// get the listview
		expListView = (ExpandableListView) v.findViewById(R.id.recExpandable);

		mTitles = new ArrayList<String>();
		mTexts = new ArrayList<ArrayList<String>>();

		loadData(mTitles, mTexts);

		// preparing list data
		prepareListData();

		listAdapter = new RecordsExpandableListAdapter(getActivity(),
				listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub

				return false;
			}
		});
		return v;
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, ArrayList<String>>();

		listDataHeader = mTitles;
		for (int i = 0; i < mTitles.size(); i++) {
			listDataChild.put(mTitles.get(i), mTexts.get(i));
		}

	}

	public void loadData(ArrayList<String> titles,
			ArrayList<ArrayList<String>> texts) {
		int counter = 0;
		ArrayList<String> array = null;
		try {
			InputStream stream = getActivity().getAssets().open("records.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			for (;;) {
				String line = in.readLine();

				if (line == null) {
					texts.add(array);
					break;
				}

				if (line.compareTo("") == 0 || line.compareTo(" ") == 0
						|| line.compareTo("  ") == 0
						|| line.compareTo("\n") == 0) {
					continue;
				}

				String[] lineSplit = line.split("#");
				String[] lineSplit2 = line.split(" ");

				if (lineSplit.length > 1) {

					if (counter > 0) {

						texts.add(array);

					}

					titles.add(lineSplit[1]);
					array = new ArrayList<String>();
					counter++;
				} else {
					if (array != null && lineSplit2.length > 4) {
						array.add(line);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
