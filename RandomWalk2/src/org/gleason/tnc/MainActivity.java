package org.gleason.tnc;

import org.gleason.tnc.model.Walker;
import org.gleason.tnc.view.WalkerView;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity{
	Walker w;
	WalkerView tcanvas;
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
		setContentView(R.layout.activity_main);
		tcanvas=new WalkerView(this);
        RelativeLayout frameLayout=(RelativeLayout)findViewById(R.id.mainLayout);
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
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tcanvas.resume();
	}

	/**
	 * @return the w
	 */
	public Walker getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(Walker w) {
		this.w = w;
	}

}
