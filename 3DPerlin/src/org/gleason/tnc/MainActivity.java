package org.gleason.tnc;

import org.gleason.tnc.model.Walker;
import org.gleason.tnc.view.WalkerView;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	Walker w;
	 WalkerView tcanvas;
//	private GLSurfaceView mGLSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		w = new Walker();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		w.setHeight(size.y);
		w.setWidth(size.x);
		w.refresh();
//		mGLSurfaceView = new GLSurfaceView(this);
//
//		// Check if the system supports OpenGL ES 2.0.
//		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//		final ConfigurationInfo configurationInfo = activityManager
//				.getDeviceConfigurationInfo();
//		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
//
//		if (supportsEs2) {
//			// Request an OpenGL ES 2.0 compatible context.
//			mGLSurfaceView.setEGLContextClientVersion(2);
//
//			// Set the renderer to our demo renderer, defined below.
//			mGLSurfaceView.setRenderer(new LessonOneRenderer());
//		} else {
//			// This is where you could create an OpenGL ES 1.x compatible
//			// renderer if you wanted to support both ES 1 and ES 2.
//			return;
//		}
//
//		setContentView(mGLSurfaceView);
		
		 setContentView(R.layout.activity_main);
		 tcanvas=new WalkerView(this);
		 RelativeLayout
		 frameLayout=(RelativeLayout)findViewById(R.id.mainLayout);
		 frameLayout.addView(tcanvas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		tcanvas.pause();
//		mGLSurfaceView.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tcanvas.resume();
//		mGLSurfaceView.onResume();
	}

	/**
	 * @return the w
	 */
	public Walker getW() {
		return w;
	}

	/**
	 * @param w
	 *            the w to set
	 */
	public void setW(Walker w) {
		this.w = w;
	}

}
