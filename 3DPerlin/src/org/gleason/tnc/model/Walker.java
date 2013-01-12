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
	private int color = 0;
	private int color2 = 0;
	private int color3 = 0;
	
	
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
		drawTexture(c);
	}
	
	float increment = 0;
	
	public void drawTexture(Canvas c){
		float xoff = 0.0f;
		for(int x = 0; x < width; x++){
			float yoff=0.0f;
			for(int y = 0; y <height; y++){
				int bright = Math.round(map((float)PerlinNoiseGenerator.noise(xoff, yoff, increment),-1f,1f,0f,255f));
				Paint myPaint = new Paint();
				myPaint.setColor(Color.rgb(bright, bright, bright));
				c.drawPoint(x, y, myPaint);
				yoff += 0.01;
			}
			xoff += 0.01;
		}
		increment += .09;
	}
	
	public void drawEllipse(Canvas c){
		RectF rect = new RectF(getX(), getY(), getX()+16, getY()+16);
		Paint myPaint = new Paint();
		myPaint.setColor(Color.rgb(color,color2,color3));
//		myPaint.setColor(Color.GRAY);
		myPaint.setStrokeWidth(1);
		c.drawOval(rect, myPaint);
	}
	
	public void drawCircle(Canvas c){
		Paint myPaint = new Paint();
		myPaint.setColor(Color.rgb(color,0,0));
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
	float tc = 1000;
	float tc2 = 1000000;
	float tc3 = 100000000;
	float yStep = 0;
	private void get1DPerlin(){
		float valX = (float)PerlinNoiseGenerator.noise(tx);
		float valY = (float)PerlinNoiseGenerator.noise(ty);
		float valC = (float)PerlinNoiseGenerator.noise(tc);
		float valC2 = (float)PerlinNoiseGenerator.noise(tc2);
		float valC3 = (float)PerlinNoiseGenerator.noise(tc3);
		setX(map(valX,0,1,0,(float)width)+(width/2));
		float yVal = map(valY,0,1,0,(float)height);
		setY(map(valY,0,1,0,(float)height));
		setY(height/2+getY());
		color = Math.round(map(valC,-1,1,0,255));
		color2 = Math.round(map(valC2,-1,1,0,255));
		color3 = Math.round(map(valC3,-1,1,0,255));
		yStep += .01;
		x += .01;
		tx += .0009;
		ty += .0009;
		tc += .0009;
		tc2 += .0005;
		tc3 += .05;
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
