package org.gleason.tnc.model;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Walker{
	private float x;
	private float y;
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
		drawEllipse(c);
	}
	
	public void drawEllipse(Canvas c){
		RectF rect = new RectF(getX(), 180, getX()+16, 180+16);
		Paint myPaint = new Paint();
		myPaint.setColor(Color.GREEN);
		myPaint.setStrokeWidth(1);
		c.drawOval(rect, myPaint);
	}
	
	public void drawCircle(Canvas c){
		Paint myPaint = new Paint();
		myPaint.setColor(Color.GREEN);
		myPaint.setStrokeWidth(1);
		c.drawCircle(getX(), getY(), 10, myPaint);
	}
	
	public void step(){
		normalDistribution();
	}
	
	private void normalDistribution(){
		Random gen = new Random();
		float num = (float) gen.nextGaussian();
		float sd = 60;
		float mean = 320;
		setX(num*sd+mean);
	}
	
	private void nineWayRandomWalk(){
		Random xRandom = new Random(3);
		Random yRandom = new Random(3);
		int xStep = ((int)Math.round(Math.random() * 6)-3);
		int yStep = ((int)Math.round(Math.random() * 6)-3);
		x= x + xStep;
		y = y+ yStep;
	}

	/**
	 * @return the y
	 */
	public float getY() {
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
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}
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
