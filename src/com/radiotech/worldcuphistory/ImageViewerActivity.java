package com.radiotech.worldcuphistory;


import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class ImageViewerActivity extends ActionBarActivity {


	
	public static final String IMAGE_URL = "image_url";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.fragment_image_viewer);
	    
	    TouchImageView largeImage = (TouchImageView) findViewById(R.id.largeImage);
	    
	   // final ImageView largeImage = (ImageView)findViewById(R.id.largeImage);
	    String imagePath = getIntent().getStringExtra(IMAGE_URL);
	    Object[] o = new Object[2];
		o[0] = largeImage;
		o[1] = imagePath;
		new LoadImage().execute(o);
		largeImage.maintainZoomAfterSetImage(false);
		
		
		
	}
	
	
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}
	
	



	@Override
	protected boolean onPrepareOptionsPanel(View view, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_MENU){
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}





	class LoadImage extends AsyncTask<Object, Void, Bitmap> {
		private TouchImageView imv;
		private String path;

		@Override
		protected Bitmap doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			Bitmap bm = null;
			imv = (TouchImageView) arg0[0];
			path = (String) arg0[1];
			try {
				InputStream ims = getAssets().open(path);
				bm = BitmapFactory.decodeStream(ims);
			

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
	
	
	
}
