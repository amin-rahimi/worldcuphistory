package com.radiotech.worldcuphistory;

public class GroupTable {
	private String mCountryName;
	private String mCountryFlagName;
	private int mWon;
	private int mDrawn;
	private int mLost;
	private int mPoints;

	public String getCountryName() {
		return mCountryName;
	}

	public void setCountryName(String countryName) {
		mCountryName = countryName;
	}

	public String getCountryFlagName() {
		return mCountryFlagName;
	}

	public void setCountryFlagName(String countryFlagName) {
		mCountryFlagName = countryFlagName;
	}

	public int getWon() {
		return mWon;
	}

	public void setWon(int won) {
		mWon = won;
	}

	public int getDrawn() {
		return mDrawn;
	}

	public void setDrawn(int drawn) {
		mDrawn = drawn;
	}

	public int getLost() {
		return mLost;
	}

	public void setLost(int lost) {
		mLost = lost;
	}

	public int getPoints() {
		return mPoints;
	}

	public void setPoints(int points) {
		mPoints = points;
	}

}
