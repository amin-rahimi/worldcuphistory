package com.radiotech.worldcuphistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {
	public static WorldCupItem worldcup;

	public DescriptionFragment() {
	}

	public static DescriptionFragment newInstance(WorldCupItem worldcup) {
		DescriptionFragment fragment = new DescriptionFragment();
		Bundle args = new Bundle();
		args.putSerializable(WorldCupFragment.WORLD_CUP_ITEM, worldcup);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		worldcup = (WorldCupItem) getArguments().getSerializable(
				WorldCupFragment.WORLD_CUP_ITEM);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_description, container,
				false);
		ImageView imgView = (ImageView) v
				.findViewById(R.id.imageViewDescription);
		TextView mTextView = (TextView) v.findViewById(R.id.textViewTitle);
		mTextView.setText(getResources().getString(R.string.worldcup) + " "
				+ String.valueOf(worldcup.getYear()));

		TextView mTextViewYear = (TextView) v
				.findViewById(R.id.textViewCountry);
		mTextViewYear.setText(worldcup.getCountryNameRes());

		String pathDesc = String.valueOf(worldcup.getYear()) + "/TXT/"
				+ "2-Description.txt";
		String statDesc = String.valueOf(worldcup.getYear()) + "/TXT/"
				+ "7-Statistics.txt";
		String recDesc = String.valueOf(worldcup.getYear()) + "/TXT/"
				+ "6-Records.txt";

		TextView mTextViewCountry = (TextView) v
				.findViewById(R.id.textViewWorldCupDescription);
		mTextViewCountry.setText(LoadData(pathDesc));

		TextView mTextViewStat = (TextView) v
				.findViewById(R.id.textViewWorldCupStatictist);
		mTextViewStat.setText(loadDataLines(statDesc));

		TextView mTextViewRec = (TextView) v
				.findViewById(R.id.textViewWorldCupRecords);
		mTextViewRec.setText(loadDataLines(recDesc));

		TextView box = (TextView)v.findViewById(R.id.textViewRecordsBox);
		
		if(worldcup.getYear() == 2018){
			mTextViewRec.setVisibility(View.GONE);
			box.setVisibility(View.GONE);
			
		}else{
			mTextViewRec.setVisibility(View.VISIBLE);
			box.setVisibility(View.VISIBLE);
		}
		
		try {
			String path = String.valueOf(worldcup.getYear()) + "/Cover/"
					+ "cover.png";
			InputStream ims = getActivity().getAssets().open(path);
			Drawable d = Drawable.createFromStream(ims, null);
			imgView.setImageDrawable(d);
		} catch (Exception e) {

			e.printStackTrace();
		}
		final String passPath = String.valueOf(worldcup.getYear()) + "/Cover/"
				+ "cover.png";
		imgView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), ImageViewerActivity.class);
				i.putExtra(ImageViewerActivity.IMAGE_URL, passPath);
				startActivity(i);
			}
		});

		return v;
	}

	public String LoadData(String inFile) {
		String tContents = "";

		try {
			InputStream stream = getActivity().getAssets().open(inFile);

			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			tContents = new String(buffer);
		} catch (IOException e) {
			// Handle exceptions here
		}

		return tContents;

	}

	public String loadDataLines(String inFile) {
		String string = "";
		try {
			InputStream stream = getActivity().getAssets().open(inFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			for (;;) {
				String line = in.readLine();

				if (line == null || line.compareTo("") == 0) {
					break;
				} else {
					line = line.replace("#", "");
					string += line + "\n";
				}
			}

		} catch (IOException e) {
			// Handle exceptions here
		}
		return string;

	}
	
	public ArrayList<String> getDataLines(String inFile){
		ArrayList<String> array = new ArrayList<String>();
		try {
			InputStream stream = getActivity().getAssets().open(inFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			for (;;) {
				String line = in.readLine();

				if (line == null || line.compareTo("") == 0) {
					break;
				} else {
					line = line.replace("#", "");
					array.add(line);
				}
			}

		} catch (IOException e) {
			// Handle exceptions here
		}
		return array;
	}

}
