package com.radiotech.worldcuphistory;

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

public class FinalStagesFragment extends Fragment {

	WorldCupItem worldcup;

	FinalStageExpandableAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, ArrayList<FinalMatchesData>> listDataChild;
	private ArrayList<ArrayList<FinalMatchesData>> mfinalStageInfo;
	private ArrayList<String> mStageNames;

	public FinalStagesFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		worldcup = (WorldCupItem) getArguments().getSerializable(
				WorldCupFragment.WORLD_CUP_ITEM);

	}

	public static FinalStagesFragment newInstance(WorldCupItem worldcup) {
		FinalStagesFragment fragment = new FinalStagesFragment();
		Bundle args = new Bundle();
		args.putSerializable(WorldCupFragment.WORLD_CUP_ITEM, worldcup);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_final_stage, container,
				false);

		String path = String.valueOf(worldcup.getYear()) + "/TXT/"
				+ "5-Knockout stage.txt";

		FinalStageMatchesFactory fsmf = new FinalStageMatchesFactory(
				getActivity(), path);

		mfinalStageInfo = fsmf.getFinalStageInfo();
		mStageNames = fsmf.getStagesNames();

		// get the listview
		expListView = (ExpandableListView) v
				.findViewById(R.id.finalStageExpandble);

		prepareListData();

		// preparing list data
		listAdapter = new FinalStageExpandableAdapter(getActivity(),
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
		// TODO Auto-generated method stub
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, ArrayList<FinalMatchesData>>();

		listDataHeader = (List<String>) mStageNames;
		for (int i = 0; i < listDataHeader.size(); i++) {
			listDataChild.put(listDataHeader.get(i), mfinalStageInfo.get(i));
		}

	}

}
