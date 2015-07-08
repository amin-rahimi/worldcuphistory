package com.radiotech.worldcuphistory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.Settings.Global;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, GroupModel> _listDataChild;
	private HashMap<String, String> countryDict;
	private HashMap<String, Integer> flagDict;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, GroupModel> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
		countryDict = loadCountryDic();
		flagDict = loadFlags();

	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition));
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		GroupModel child = (GroupModel) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_item, null);
		}
		
		
		TextView nonGroup = (TextView) convertView
				.findViewById(R.id.textViewnongroup);
		nonGroup.setVisibility(View.GONE);
		
		TableLayout matchesTable = (TableLayout) convertView
				.findViewById(R.id.machesTable);

		if (child.getGroupTable().size() == 1) {
			TableLayout tbl = (TableLayout) convertView
					.findViewById(R.id.tblayout);
			tbl.setVisibility(View.GONE);
			nonGroup.setVisibility(View.VISIBLE);
			matchesTable.setVisibility(View.GONE);
			return convertView;
		} else {
			nonGroup.setVisibility(View.GONE);
			TableLayout tbl = (TableLayout) convertView
					.findViewById(R.id.tblayout);
			tbl.setVisibility(View.VISIBLE);
			matchesTable.setVisibility(View.VISIBLE);
		}
		
		
		// ////////////////////////////////////////////////////////////////////////
		ArrayList<TableRow> tables = new ArrayList<TableRow>();
		TableRow tb1 = (TableRow) convertView.findViewById(R.id.tableRow1);
		TableRow tb2 = (TableRow) convertView.findViewById(R.id.tableRow2);
		TableRow tb3 = (TableRow) convertView.findViewById(R.id.tableRow3);
		TableRow tb4 = (TableRow) convertView.findViewById(R.id.tableRow4);
		tb1.setBackgroundColor(Color.parseColor("#f9fff0"));
		tb2.setBackgroundColor(Color.parseColor("#f9fff0"));
		tables.add(tb1);
		tables.add(tb2);
		tables.add(tb3);
		tables.add(tb4);

		if (GroupsFragment.worldcup.getYear() <= 1954) {
			tb2.setBackgroundColor(Color.parseColor("#ffffff"));
		}

		if (child.getGroupTable().size() == 3) {
			tb4.setVisibility(View.GONE);
			tb3.setVisibility(View.VISIBLE);
		} else if (child.getGroupTable().size() == 2) {
			tb3.setVisibility(View.GONE);
			tb4.setVisibility(View.GONE);
		} else {
			tb3.setVisibility(View.VISIBLE);
			tb4.setVisibility(View.VISIBLE);
		}
		for (int i = 0; i < child.getGroupTable().size(); i++) {

			GroupTable gt = child.getGroupTable().get(i);
			ImageView imv = (ImageView) tables.get(i).getChildAt(0);
			TextView tsv1 = (TextView) tables.get(i).getChildAt(1);
			TextView tsv2 = (TextView) tables.get(i).getChildAt(2);
			TextView tsv3 = (TextView) tables.get(i).getChildAt(3);
			TextView tsv4 = (TextView) tables.get(i).getChildAt(4);
			TextView tsv5 = (TextView) tables.get(i).getChildAt(5);

			String farsiName = countryDict.get(gt.getCountryName());
			if (farsiName != null) {
				tsv1.setText(countryDict.get(gt.getCountryName()));
			} else {
				tsv1.setText(gt.getCountryName());
			}
			imv.setImageResource(flagDict.get(gt.getCountryName()));

			tsv2.setText(String.valueOf(gt.getWon()));
			tsv3.setText(String.valueOf(gt.getDrawn()));
			tsv4.setText(String.valueOf(gt.getLost()));
			tsv5.setText(String.valueOf(gt.getPoints()));

		}
		// ///////////////////////////////////////////////////////////////////////

		

		// //////////////////////////////////////////////////////////////////////////
		LayoutParams layoutP = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutP.gravity = Gravity.CENTER;
		matchesTable.removeAllViews();

		for (int i = 0; i < child.getGroupData().size(); i++) {
			// /////////////////////////
			GroupData gData = child.getGroupData().get(i);
			TableRow tr = new TableRow(_context);
			TableRow.LayoutParams trParams = new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			trParams.gravity = Gravity.CENTER;
			tr.setGravity(Gravity.CENTER);
			tr.setPadding(5, 20, 5, 20);
			tr.setLayoutParams(trParams);

			// //////////////////////////////////
			ImageView leftImageview = new ImageView(_context);
			leftImageview.setLayoutParams(new LayoutParams(30, 30));
			leftImageview
					.setImageResource(flagDict.get(gData.getLeftCountery()));

			// ///////////////////////////////////////////
			ImageView rightImageview = new ImageView(_context);
			rightImageview.setLayoutParams(new LayoutParams(30, 30));
			rightImageview.setImageResource(flagDict.get(gData
					.getRightCountery()));

			// //////////////////////////////////////////
			TextView leftCountry = new TextView(_context);
			leftCountry.setLayoutParams(layoutP);
			leftCountry.setText(countryDict.get(gData.getLeftCountery()));
			// leftCountry.setTypeface(null, Typeface.BOLD);
			leftCountry.setTextSize(14);
			leftCountry.setTextColor(Color.parseColor("#666666"));
			// //////////////////////////////////////////////
			TextView rightCountry = new TextView(_context);
			rightCountry.setLayoutParams(layoutP);
			rightCountry.setText(countryDict.get(gData.getRightCountery()));
			// rightCountry.setTypeface(null, Typeface.BOLD);
			rightCountry.setTextSize(14);
			rightCountry.setTextColor(Color.parseColor("#666666"));

			// ////////////////////////////////////////////
			TextView dashtxv = new TextView(_context);
			dashtxv.setTextSize(12);
			dashtxv.setText(" - ");
			dashtxv.setLayoutParams(layoutP);

			TextView dashtxv2 = new TextView(_context);
			dashtxv2.setTextSize(12);
			dashtxv2.setText(" - ");
			dashtxv2.setLayoutParams(layoutP);
			// //////////////////////////////////////////
			TextView noghtetv = new TextView(_context);
			noghtetv.setTextSize(12);
			noghtetv.setText(" : ");
			noghtetv.setLayoutParams(layoutP);

			TextView space1 = new TextView(_context);
			space1.setTextSize(12);
			space1.setText("  ");
			space1.setLayoutParams(layoutP);

			TextView space2 = new TextView(_context);
			space2.setTextSize(12);
			space2.setText("  ");
			space2.setLayoutParams(layoutP);

			// /////////////////////////////////////////
			TextView goalLeft = new TextView(_context);
			goalLeft.setLayoutParams(layoutP);
			goalLeft.setTypeface(null, Typeface.BOLD);
			goalLeft.setTextSize(14);
			goalLeft.setTextColor(Color.parseColor("#207bc2"));
			goalLeft.setText(String.valueOf(gData.getLeftGoals()));

			// //////////////////////////////////////
			TextView goalRight = new TextView(_context);
			goalRight.setLayoutParams(layoutP);
			goalRight.setTypeface(null, Typeface.BOLD);
			goalRight.setTextSize(14);
			goalRight.setTextColor(Color.parseColor("#207bc2"));
			goalRight.setText(String.valueOf(gData.getRightGoals()));

			tr.addView(leftImageview);
			tr.addView(space1);
			tr.addView(leftCountry);
			tr.addView(dashtxv);
			tr.addView(goalLeft);
			tr.addView(noghtetv);
			tr.addView(goalRight);
			tr.addView(dashtxv2);
			tr.addView(rightCountry);
			tr.addView(space2);
			tr.addView(rightImageview);

			matchesTable.addView(tr);

		}

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public HashMap<String, String> loadCountryDic() {
		HashMap<String, String> countryDic = new HashMap<String, String>();
		try {

			InputStream stream = _context.getAssets().open("dic.txt");
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

			InputStream stream = _context.getAssets().open("dic.txt");
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

}
