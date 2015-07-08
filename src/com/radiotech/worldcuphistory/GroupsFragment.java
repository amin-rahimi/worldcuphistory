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
import android.widget.Toast;
import android.app.Activity;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;



public class GroupsFragment extends Fragment {
	public static WorldCupItem worldcup;
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, GroupModel> listDataChild;
    
    private ArrayList<GroupModel> mGroupsInfo;
    
	public GroupsFragment() {
	}

	public static GroupsFragment newInstance(WorldCupItem worldcup) {
		GroupsFragment fragment = new GroupsFragment();
		Bundle args = new Bundle();
		args.putSerializable(WorldCupFragment.WORLD_CUP_ITEM, worldcup);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_groups, container, false);
		String path = String.valueOf(worldcup.getYear()) + "/TXT/"
				+ "4-Groups Results.txt";
		
		GroupModelFactory gmf = new GroupModelFactory(getActivity(), path);
		mGroupsInfo = gmf.getGroupsInfo();
	
		
		
		// get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
 
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		worldcup = (WorldCupItem)getArguments().getSerializable(WorldCupFragment.WORLD_CUP_ITEM);
	}
	
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, GroupModel>();
        
        String[] groupsNumber ={"گروه یک", "گروه دو", "گروه سه", "گروه چهار", "گروه پنج", "گروه شش", "گروه هفت", "گروه هشت"};
        
        
        for(int i = 0; i < mGroupsInfo.size();i++){
        	listDataHeader.add(groupsNumber[i]);
        }
        
       for(int j = 0; j < mGroupsInfo.size();j++){
    	   listDataChild.put(listDataHeader.get(j), mGroupsInfo.get(j));
       }
 
    }

}
