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

public class FinalStageExpandableAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, ArrayList<FinalMatchesData>> _listDataChild;
	private HashMap<String, String> countryDict;
	private HashMap<String, Integer> flagDict;

	public FinalStageExpandableAdapter(Context context,
			List<String> listDataHeader,
			HashMap<String, ArrayList<FinalMatchesData>> listChildData) {
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

		@SuppressWarnings("unchecked")
		ArrayList<FinalMatchesData> child = (ArrayList<FinalMatchesData>) getChild(
				groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater
					.inflate(R.layout.final_stage_item, null);
		}

		TableLayout finalStageTable = (TableLayout) convertView
				.findViewById(R.id.finalStageTableLayout);

		TextView finaltxv = (TextView) convertView
				.findViewById(R.id.textViewFinal);

		if (child.get(child.size() - 1).getDate() != null) {
			if (child.get(child.size() - 1).getDate().compareTo("final") == 0) {
				finaltxv.setVisibility(View.VISIBLE);
				finaltxv.setText(child.get(child.size() - 1).getText());
			} else {
				finaltxv.setText("");
			}
		} else {
			finaltxv.setText("");
			finaltxv.setVisibility(View.GONE);
		}
		// finalStageTable.setGravity(Gravity.CENTER);

		// //////////////////////////////////////////////////////////////////////////
		LayoutParams layoutP = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layoutP.gravity = Gravity.CENTER;
		
		finalStageTable.removeAllViews();

		for (int i = 0; i < child.size(); i++) {
			// ////////////////////////

			FinalMatchesData fData = child.get(i);
			TableRow tr = new TableRow(_context);
			TableRow.LayoutParams trParams = new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			trParams.gravity = Gravity.CENTER;
			tr.setGravity(Gravity.CENTER);
			tr.setPadding(5, 20, 5, 20);
			tr.setLayoutParams(trParams);

			// //////////////////////////////////////////
		
			TableRow tr2 = new TableRow(_context);
			TableRow.LayoutParams trParams2 = new TableRow.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			trParams2.span = 10;
			trParams2.gravity = Gravity.LEFT;

			tr2.setGravity(Gravity.CENTER);
			tr2.setPadding(5, 5, 5, 5);
			tr2.setLayoutParams(trParams2);
			
			
			TextView info = new TextView(_context);
			info.setLayoutParams(layoutP);
			info.setTextSize(14);
			info.setTextColor(Color.parseColor("#be0000"));
			if (fData.getDate() != null) {
				if (fData.getDate().compareTo("info") == 0) {
					info.setText(fData.getText());
					info.setLayoutParams(trParams2);
					
					tr2.addView(info);
					
				}
			} else {
				info.setText("");
				tr2.removeAllViews();
			}

			// //////////////////////////////////
			ImageView leftImageview = new ImageView(_context);
			leftImageview.setLayoutParams(new LayoutParams(30, 30));
			leftImageview
					.setImageResource(flagDict.get(fData.getLeftCountery()));

			// ///////////////////////////////////////////
			ImageView rightImageview = new ImageView(_context);
			rightImageview.setLayoutParams(new LayoutParams(30, 30));
			rightImageview.setImageResource(flagDict.get(fData
					.getRightCountery()));

			// //////////////////////////////////////////
			TextView leftCountry = new TextView(_context);
			leftCountry.setLayoutParams(layoutP);
			leftCountry.setText(countryDict.get(fData.getLeftCountery()));
			leftCountry.setTextSize(14);
			leftCountry.setTextColor(Color.parseColor("#666666"));
			// //////////////////////////////////////////////
			TextView rightCountry = new TextView(_context);
			rightCountry.setLayoutParams(layoutP);
			rightCountry.setText(countryDict.get(fData.getRightCountery()));
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
			goalLeft.setText(fData.getLeftGoals());

			// //////////////////////////////////////
			TextView goalRight = new TextView(_context);
			goalRight.setLayoutParams(layoutP);
			goalRight.setTypeface(null, Typeface.BOLD);
			goalRight.setTextSize(14);
			goalRight.setTextColor(Color.parseColor("#207bc2"));
			goalRight.setText(fData.getRightGoals());

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
			finalStageTable.addView(tr);
			if (fData.getDate() != null) {
				if (fData.getDate().compareTo("info") == 0) {
					finalStageTable.addView(tr2);
				}
			}

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
