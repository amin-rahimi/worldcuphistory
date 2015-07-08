package com.radiotech.worldcuphistory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

public class GroupModelFactory {

	private ArrayList<GroupModel> mGroupsInfo;
	private HashMap<String, String> mCountryDict;

	private Context mContext;

	public GroupModelFactory(Context context, String path) {
		mContext = context;
		populateGroups(path);
	}

	public ArrayList<GroupModel> getGroupsInfo() {
		return mGroupsInfo;
	}

	private void populateGroups(String path) {
		mGroupsInfo = new ArrayList<GroupModel>();
		GroupData gData = null;
		GroupTable gTable = null;
		GroupModel gModel = null;
		ArrayList<GroupData> groupDataArray = null;
		ArrayList<GroupTable> groupTableArray = null;
		boolean groupTableBound = false;
		boolean groupDataBound = false;
		int counter = 0;
		try {
			InputStream stream = mContext.getAssets().open(path);
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

					if (lineSplit[1].toLowerCase().compareTo("team") == 0) {
						if (counter > 0) {
							gModel.setGroupData(groupDataArray);
							gModel.setGroupTable(groupTableArray);
							mGroupsInfo.add(gModel);
						}
						groupDataArray = new ArrayList<GroupData>();
						groupTableArray = new ArrayList<GroupTable>();
						gModel = new GroupModel();
						groupDataBound = false;
						groupTableBound = true;
						counter++;
					}

					if (lineSplit[1].toLowerCase().compareTo("result") == 0) {
						groupDataBound = true;
						groupTableBound = false;
					}

					if (lineSplit[1].toLowerCase().compareTo("team") != 0
							&& lineSplit.length == 7 && groupTableBound) {
						gTable = new GroupTable();
						gTable.setCountryFlagName("");
						gTable.setCountryName(lineSplit[1]);
						gTable.setWon(Integer.parseInt(lineSplit[3]));
						gTable.setDrawn(Integer.parseInt(lineSplit[4]));
						gTable.setLost(Integer.parseInt(lineSplit[5]));
						gTable.setPoints(Integer.parseInt(lineSplit[6]));
						groupTableArray.add(gTable);

					}

					if (lineSplit.length == 5 && groupDataBound) {
						gData = new GroupData();
						gData.setLeftCountery(lineSplit[1]);
						gData.setLeftCountryFlagName("");
						gData.setLeftGoals(Integer.parseInt(lineSplit[2]));
						gData.setRightGoals(Integer.parseInt(lineSplit[3]));
						gData.setRightCountery(lineSplit[4]);
						gData.setRightCountryFlagName("");
						groupDataArray.add(gData);
					}
				}

			}
			
			if (groupDataArray != null && groupTableArray != null) {
				gModel.setGroupData(groupDataArray);
				gModel.setGroupTable(groupTableArray);
				mGroupsInfo.add(gModel);
			}else{
				groupDataArray = new ArrayList<GroupData>();
				groupTableArray = new ArrayList<GroupTable>();
				gModel = new GroupModel();
				groupDataArray.add(new GroupData());
				groupTableArray.add(new GroupTable());
				gModel.setGroupData(groupDataArray);
				gModel.setGroupTable(groupTableArray);
				mGroupsInfo.add(gModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
