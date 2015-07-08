package com.radiotech.worldcuphistory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.TextView;

public class HistoryFragment extends Fragment {

	public HistoryFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_history, container, false);

		GridView myGridView = (GridView) v
				.findViewById(R.id.gridViewPresidents);
		String path = "president";
		String[] list = getAllImages(path);
		ArrayList<String> paths = new ArrayList<String>();
		for (String item : list) {
			paths.add(path + "/" + item);
		}
		GalleryItemAdapter adapter = new GalleryItemAdapter(paths);
		myGridView.setAdapter(adapter);

		final ArrayList<String> pathsFinal = paths;

		myGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), ImageViewerActivity.class);
				final String path = pathsFinal.get(position);
				i.putExtra(ImageViewerActivity.IMAGE_URL, path);
				startActivity(i);
			}
		});
		
		TextView desc = (TextView)v.findViewById(R.id.textViewHistory);
		desc.setText(LoadData("start.txt"));

		return v;

	}

	private class GalleryItemAdapter extends ArrayAdapter<String> {
		public GalleryItemAdapter(ArrayList<String> items) {
			super(getActivity(), 0, items);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.pictures_item, parent, false);
			}

			String item = getItem(position);
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.gallery_item_imageView);

			Object[] o = new Object[2];
			o[0] = imageView;
			o[1] = item;
			new LoadImage().execute(o);

			return convertView;
		}
	}

	class LoadImage extends AsyncTask<Object, Void, Bitmap> {
		private ImageView imv;
		private String path;

		@Override
		protected Bitmap doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			Bitmap bm = null;
			imv = (ImageView) arg0[0];
			path = (String) arg0[1];
			try {
				InputStream ims = getActivity().getAssets().open(path);
				bm = BitmapEfficiently.decodeSampledBitmapFromResource(ims,
						120, 120);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bm;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			imv.setImageBitmap(result);
		}

	}

	private String[] getAllImages(String path) {
		String[] array = null;
		try {
			array = getActivity().getAssets().list(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
		ListAdapter listAdapter = gridView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		int items = listAdapter.getCount();
		int rows = 0;

		View listItem = listAdapter.getView(0, null, gridView);
		listItem.measure(0, 0);
		totalHeight = listItem.getMeasuredHeight();

		float x = 1;
		if (items > columns) {
			x = items / columns;
			rows = (int) (x + 1);
			totalHeight *= rows;
		}

		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		params.height = totalHeight;
		gridView.setLayoutParams(params);

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

}
