package com.radiotech.worldcuphistory;

import java.io.Serializable;

public class WorldCupItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5625777761281595130L;
	private int mCountryNameRes;
	private int mDescriptionRes;
	private int mFlagSource;
	private int mYear;

	public WorldCupItem(int countryName, int description, int flagSource, int year){
		mCountryNameRes = countryName;
		mDescriptionRes = description;
		mFlagSource = flagSource;
		mYear = year;
	}

	public int getCountryNameRes() {
		return mCountryNameRes;
	}

	public void setCountryNameRes(int countryNameRes) {
		mCountryNameRes = countryNameRes;
	}

	public int getDescriptionRes() {
		return mDescriptionRes;
	}

	public void setDescriptionRes(int descriptionRes) {
		mDescriptionRes = descriptionRes;
	}

	public int getFlagSource() {
		return mFlagSource;
	}

	public void setFlagSource(int flagSource) {
		mFlagSource = flagSource;
	}

	public int getYear() {
		return mYear;
	}

	public void setYear(int year) {
		mYear = year;
	}
	

}
