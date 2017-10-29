package main.se450.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import main.se450.collections.LineCollection;
import main.se450.interfaces.ICollided;
import main.se450.interfaces.IShot;
import main.se450.interfaces.IStrategy;

public class Shot extends Circle implements IShot, ICollided{

	private Ellipse2D circle=new Ellipse2D.Float (0.0f,0.0f,0.0f,0.0f) ;
	private int lifetime=0;
	private boolean collided= false;
	
	public Shot(int nlifetime, float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor,
			IStrategy iStrategy) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		// TODO Auto-generated constructor stub
		lifetime=nlifetime;
	}

	public void update(){
		--lifetime;
		super.update();
	}
	
	@Override
	public boolean hasCollided() {
		// TODO Auto-generated method stub
		return collided;
	}

	@Override
	public void collide() {
		// TODO Auto-generated method stub
		collided = true;
	}

	@Override
	public void addSides(LineCollection linecollection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExpired() {
		// TODO Auto-generated method stub
		return lifetime<0;
	}

	@Override
	public LineCollection getLineCollection() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
