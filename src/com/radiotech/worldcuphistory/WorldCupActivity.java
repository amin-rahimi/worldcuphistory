package com.radiotech.worldcuphistory;

import java.util.ArrayList;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WorldCupActivity extends ActionBarActivity {
	private ArrayList<WorldCupItem> mWorldCupItems;

	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private CharSequence mTitle;
	private ListView worldCupListView;
	private WorldCupAdapter adapter;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		mDrawerLayout.openDrawer(Gravity.LEFT);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_world_cup);

		if (savedInstanceState == null) {
			FirstPageFragment fragment = new FirstPageFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
		}
		mWorldCupItems = new ArrayList<WorldCupItem>();

		mWorldCupItems.add(new WorldCupItem(R.string.country_uruguay, 0,
				R.drawable.uruguay, 1930));
		mWorldCupItems.add(new WorldCupItem(R.string.country_italy, 0,
				R.drawable.italy, 1934));
		mWorldCupItems.add(new WorldCupItem(R.string.country_france, 0,
				R.drawable.france, 1938));
		mWorldCupItems.add(new WorldCupItem(R.string.country_brazil, 0,
				R.drawable.brazil, 1950));
		mWorldCupItems.add(new WorldCupItem(R.string.country_switzerland, 0,
				R.drawable.switzerland, 1954));
		mWorldCupItems.add(new WorldCupItem(R.string.country_sweden, 0,
				R.drawable.sweden, 1958));
		mWorldCupItems.add(new WorldCupItem(R.string.country_chile, 0,
				R.drawable.chile, 1962));
		mWorldCupItems.add(new WorldCupItem(R.string.country_united_kindom, 0,
				R.drawable.united_kingdom, 1966));
		mWorldCupItems.add(new WorldCupItem(R.string.country_mexico, 0,
				R.drawable.mexico, 1970));
		mWorldCupItems.add(new WorldCupItem(R.string.country_west_germany, 0,
				R.drawable.germany, 1974));
		mWorldCupItems.add(new WorldCupItem(R.string.country_argentina, 0,
				R.drawable.argentina, 1978));
		mWorldCupItems.add(new WorldCupItem(R.string.country_spain, 0,
				R.drawable.spain, 1982));
		mWorldCupItems.add(new WorldCupItem(R.string.country_mexico, 0,
				R.drawable.mexico, 1986));
		mWorldCupItems.add(new WorldCupItem(R.string.country_italy, 0,
				R.drawable.italy, 1990));
		mWorldCupItems.add(new WorldCupItem(R.string.country_usa, 0,
				R.drawable.united_states, 1994));
		mWorldCupItems.add(new WorldCupItem(R.string.country_france, 0,
				R.drawable.france, 1998));
		mWorldCupItems.add(new WorldCupItem(R.string.country_japon_korea, 0,
				R.drawable.japan_korea, 2002));
		mWorldCupItems.add(new WorldCupItem(R.string.country_germany, 0,
				R.drawable.germany, 2006));
		mWorldCupItems.add(new WorldCupItem(R.string.country_south_africa, 0,
				R.drawable.south_africa, 2010));
		mWorldCupItems.add(new WorldCupItem(R.string.country_brazil, 0,
				R.drawable.brazil, 2014));

		worldCupListView = (ListView) findViewById(R.id.left_drawer);
		adapter = new WorldCupAdapter(this, mWorldCupItems);
		worldCupListView.setAdapter(adapter);

		mTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				if (DescriptionFragment.worldcup != null) {
					setTitle("جام جهانی"
							+ " "
							+ String.valueOf(DescriptionFragment.worldcup
									.getYear()));
					getSupportActionBar().setSubtitle(
							getResources().getString(
									DescriptionFragment.worldcup
											.getCountryNameRes()));
				}
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(
						getResources().getString(R.string.app_name));
				getSupportActionBar().setSubtitle("فهرست جام های جهانی");
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}

		};

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerLayout.setScrimColor(getResources().getColor(
				android.R.color.transparent));
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		worldCupListView.setOnItemClickListener(new DrawerItemClickListener());

	}

	/* The click listener for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			WorldCupItem worldcup = adapter.getItem(position);
			selectItem(worldcup, position);
		}
	}

	public void selectItem(WorldCupItem worldcup, int position) {
		// update the main content by replacing fragments

		WorldCupFragment fragment = new WorldCupFragment();
		Bundle args = new Bundle();
		args.putSerializable(WorldCupFragment.WORLD_CUP_ITEM, worldcup);
		fragment.setArguments(args);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		worldCupListView.setItemChecked(position, true);
		setTitle("جام جهانی" + " " + String.valueOf(worldcup.getYear()));
		getSupportActionBar().setSubtitle(
				getResources().getString(worldcup.getCountryNameRes()));
		mDrawerLayout.closeDrawer(worldCupListView);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.world_cup, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			this.finish();
			return true;

		case R.id.desc_settings:
			Intent i = new Intent(WorldCupActivity.this, AboutActivity.class);
			startActivity(i);
			return true;
		case R.id.homeee_settings:
			FirstPageFragment fragment = new FirstPageFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		// Handle action buttons

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public class WorldCupAdapter extends ArrayAdapter<WorldCupItem> {

		public WorldCupAdapter(Context context,
				ArrayList<WorldCupItem> worldCupItems) {
			super(context, 0, worldCupItems);
			// TODO Auto-generated constructor stub

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(
						R.layout.world_cup_list_item, null);
			}

			WorldCupItem item = getItem(position);

			RelativeLayout rl = (RelativeLayout) convertView
					.findViewById(R.id.reletive);
			if (item.getYear() == 1946) {
				rl.setBackgroundColor(Color.parseColor("#dddddd"));
			} else {
				rl.setBackgroundResource(android.R.drawable.list_selector_background);
			}
			TextView colorTextView = (TextView) convertView
					.findViewById(R.id.textView3);
			TextView yearTextView = (TextView) convertView
					.findViewById(R.id.textView1);
			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.textView2);
			ImageView flagImageView = (ImageView) convertView
					.findViewById(R.id.imageView1);

			yearTextView.setText("جام جهانی" + " "
					+ String.valueOf(item.getYear()));
			nameTextView.setText(item.getCountryNameRes());
			flagImageView.setImageResource(item.getFlagSource());

			switch (position % 8) {
			case 0:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.deep_blue));
				break;
			case 1:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.deep_purple));
				break;
			case 2:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.deep_red));
				break;
			case 3:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.light_rede));
				break;
			case 4:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.deep_orange));
				break;
			case 5:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.light_orange));
				break;
			case 6:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.yellow));
				break;
			case 7:
				colorTextView.setBackgroundColor(getResources().getColor(
						R.color.green));
				break;
			}

			return convertView;
		}

	}

}
