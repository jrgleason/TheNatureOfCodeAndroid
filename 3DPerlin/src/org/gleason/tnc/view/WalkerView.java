package org.gleason.tnc.view;

import org.gleason.tnc.MainActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WalkerView extends SurfaceView implements Runnable {
	private MainActivity a;
	private boolean running = true;
	private SurfaceHolder holder;
	private Thread t = null;

	public WalkerView(Context context) {
		super(context);
		if (context instanceof MainActivity) {
			a = (MainActivity) context;
		}
		holder = getHolder();
		resume();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (a != null) {
			a.getW().draw(canvas);
		}
	}

	@Override
	public void run() {
		boolean done = false;
		while (running) {
			if(!holder.getSurface().isValid()){
				continue;
			}
			a.getW().step();
//			if(!done){
				done = true;
				Canvas c = holder.lockCanvas();
				try{
					synchronized (c) {
						a.getW().setHeight(80);
						a.getW().setWidth(80);
						a.getW().draw(c);
					}
				}
				finally{
					holder.unlockCanvasAndPost(c);
					
				}
			}
			

//		}
	}

	public void pause() {
		running = false;
		try {
			t.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
	}

	public void resume() {
		running = true;
		t = new Thread(this);
		t.start();
	}
}
