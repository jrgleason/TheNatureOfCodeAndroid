package org.gleason.tnc.model;

import java.util.Random;

import org.gleason.tnc.util.PerlinNoiseGenerator;

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
		RectF rect = new RectF(getX(), getY(), getX()+16, getY()+16);
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
//		normalDistribution();
//		getMonteCarlo();
//		getExponential();
		get1DPerlin();
	}
	
	float tx = 0;
	float ty = 10000;
	float yStep = 0;
	private void get1DPerlin(){
		float valX = (float)PerlinNoiseGenerator.noise(tx);
		float valY = (float)PerlinNoiseGenerator.noise(ty);
		setX(map(valX,0,1,0,(float)width)+(width/2));
		float yVal = map(valY,0,1,0,(float)height);
		setY(map(valY,0,1,0,(float)height));
		setY(height/2+getY());
		yStep += .01;
		x += .01;
		tx += .01;
		ty += .0009;
	}
	
	private float map(float value, float low1, float high1, float low2, float high2){
		return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
	}
	
	private void getMonteCarlo(){
		setX(monteCarlo()*width);
		setY(monteCarlo()*height);
	}
	
	private void getExponential(){
		setX(exponential()*width);
		setY(exponential()*height);
	}
	
	private float monteCarlo(){
		while(true){
			float r1 = (float)Math.random();
			float probability = r1;
			float r2 = (float)Math.random();
			if(r2<probability){
				return r1;
			}
		}
	}
	
	private float exponential(){
		while(true){
			double r1 = Math.random();
			float probability = (float)Math.pow(r1,2);
			float r2 = (float)Math.random();
			if(r2<probability){
				return (float)r1;
			}
		}
	}
	
	private void normalDistribution(){
		Random gen = new Random();
		generateNormalX(gen);
		generateNormalY(gen);
	}
	
	private void generateNormalX(Random gen){
		float num = (float) gen.nextGaussian();
		float sd = 60;
		float mean = getWidth()/2;
		setX(num*sd+mean);
	}
	private void generateNormalY(Random gen){
		float num = (float) gen.nextGaussian();
		float sd = 60;
		float mean = getHeight()/2;
		setY(num*sd+mean);
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
	public void setY(float y) {
		this.y = y;
	}
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
