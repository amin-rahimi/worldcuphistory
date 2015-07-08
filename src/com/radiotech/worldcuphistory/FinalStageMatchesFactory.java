package com.radiotech.worldcuphistory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;

public class FinalStageMatchesFactory {

	private Context mContext;
	private ArrayList<ArrayList<FinalMatchesData>> mFinalStageInfo;
	private ArrayList<String> mStagesNames;

	public FinalStageMatchesFactory(Context context, String path) {
		mContext = context;
		populateFinalMatches(path);

	}

	public ArrayList<ArrayList<FinalMatchesData>> getFinalStageInfo() {
		return mFinalStageInfo;
	}

	public ArrayList<String> getStagesNames() {
		return mStagesNames;
	}

	private void populateFinalMatches(String path) {
		mFinalStageInfo = new ArrayList<ArrayList<FinalMatchesData>>();
		mStagesNames = new ArrayList<String>();
		FinalMatchesData fData = null;
		ArrayList<FinalMatchesData> fDataArraye = null;
		boolean isFinal = false;
		boolean isFromAbove = true;
		String finalString = "";
		int counter = 0;
		try {

			InputStream stream = mContext.getAssets().open(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			for (;;) {
				String line = in.readLine();

				if (line == null) {
					fDataArraye.get(fDataArraye.size() - 1)
							.setText(finalString);
					fDataArraye.get(fDataArraye.size() - 1)
					.setDate("final");
					mFinalStageInfo.add(fDataArraye);
					break;
				}
				if (line.compareTo("") == 0) {
					continue;
				}
				String[] lineSplit = line.split("#");
				if (lineSplit.length > 1) {

					if (lineSplit[1].compareTo("مرحله حذفی") == 0) {
						continue;
					}

					if (lineSplit[1].compareTo("پایانی") == 0) {
						isFinal = true;
						mStagesNames.add(lineSplit[1]);
						continue;
					}

					if (lineSplit.length == 2 && !isFinal) {
						mStagesNames.add(lineSplit[1]);
						if (counter > 0) {
							mFinalStageInfo.add(fDataArraye);
						}
						fDataArraye = new ArrayList<FinalMatchesData>();
						counter++;
					}

					if (lineSplit.length >= 4 && !isFinal) {

						fData = new FinalMatchesData();

						if (lineSplit.length == 4) {
							fData.setLeftCountery(lineSplit[1]);
							fData.setLeftGoals("");
							fData.setRightGoals("");
							fData.setRightCountery(lineSplit[2]);
							fData.setText(lineSplit[3]);
							fData.setDate("info");

						} else if (lineSplit.length == 6) {
							fData.setLeftCountery(lineSplit[1]);
							fData.setLeftGoals(lineSplit[2]);
							fData.setRightGoals(lineSplit[3]);
							fData.setRightCountery(lineSplit[4]);
							fData.setText(lineSplit[5]);
							fData.setDate("info");
						} else {
							fData.setLeftCountery(lineSplit[1]);
							fData.setLeftGoals(lineSplit[2]);
							fData.setRightGoals(lineSplit[3]);
							fData.setRightCountery(lineSplit[4]);
						}

						fDataArraye.add(fData);

					}

					if (isFinal) {
						if (isFromAbove) {
							if (fDataArraye != null) {
								mFinalStageInfo.add(fDataArraye);
							}
							fDataArraye = new ArrayList<FinalMatchesData>();
							isFromAbove = false;
						}

						if (lineSplit.length == 5) {
							fData = new FinalMatchesData();
							fData.setLeftCountery(lineSplit[1]);
							fData.setLeftGoals(lineSplit[2]);
							fData.setRightGoals(lineSplit[3]);
							fData.setRightCountery(lineSplit[4]);
							fDataArraye.add(fData);
						} else {
							finalString += lineSplit[1] + "\n";
						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
