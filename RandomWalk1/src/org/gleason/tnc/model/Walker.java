package org.gleason.tnc.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Walker{
	private int x;
	private int y;
	private int height=0;
	private int width=0;
	
	
	public Walker(){
	}
	
	public void refresh(){
		if(width > 0){
			setX(width/2);
		}
		if(height > 0){
			setY(height/2);
		}
	}
	
	public void draw(Canvas c){
		Paint myPaint = new Paint();
		myPaint.setColor(Color.GREEN);
		myPaint.setStrokeWidth(1);
		c.drawCircle(getX(), getY(), 10, myPaint);
	}
	
	public void step(){
		double choice = Math.random();
		if(choice>.75){
			x++;
		}
		else if(choice <.75 && choice > .5){
			x--;
		}
		else if(choice >.25 && choice < .5){
			y++;
		}
		else{
			y--;
		}
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

}
