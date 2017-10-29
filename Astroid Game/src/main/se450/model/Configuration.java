package main.se450.model;

import java.awt.Color;

import main.se450.interfaces.IStrategy;

public class Configuration {
	private int framespersecond = 0;
	private Color color = null;
	private int shipwidth = 0;
	private int shipheight = 0;
	private float shotspeed =0;
	private float forwardthrust = 0;
	private float reversethrust =0;
	private float friction = 0;
	private float leftright =0;
	private IStrategy borders = null;
	
	public Configuration (int nFrameSecond, int sw, int sh, float nss, float ft, float rt, float f, float lr, Color c, IStrategy nborders)
	{
		framespersecond = nFrameSecond;
		shipwidth = sw;
		shipheight = sh;
		shotspeed = nss;
		forwardthrust = ft;
		reversethrust = rt;
		friction = f;
		leftright = lr;
		color = c;
		borders = nborders;
	}
	
	public int getframespersecond(){
		return framespersecond;
	}

	public Color getColor() {
		return color;
	}


	public float getShotspeed() {
		return shotspeed;
	}


	public float getForwardthrust() {
		return forwardthrust;
	}


	public float getReversethrust() {
		return reversethrust;
	}


	public float getFriction() {
		return friction;
	}


	public float getLeftright() {
		return leftright;
	}

	public IStrategy getBorders() {
		return borders;
	}


	public int getFramespersecond() {
		return framespersecond;
	}

	public float getShipWidth() {
		// TODO Auto-generated method stub
		return shipwidth;
	}

	public float getShipHeight() {
		// TODO Auto-generated method stub
		return shipheight;
	}
	
	
}
