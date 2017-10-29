package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IForwardThrustObservable;
import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.ILeftObservable;
import main.se450.interfaces.IMotion;
import main.se450.interfaces.IMotionObservable;
import main.se450.interfaces.IQuitObservable;
import main.se450.interfaces.IReverseThrustObservable;
import main.se450.interfaces.IRightObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IShieldObservable;
import main.se450.interfaces.IStartObservable;
import main.se450.interfaces.IStopObservable;
import main.se450.interfaces.IStrategy;
import main.se450.observable.ForwardThrust;
import main.se450.observable.HyperSpace;
import main.se450.observable.Left;
import main.se450.observable.ReverseThrust;
import main.se450.observable.Right;
import main.se450.observable.Shield;
import main.se450.observable.Stop;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.SoundManager;
import main.se450.sound.Fire;

public class PlayerShip  extends Ship implements IMotion, IFireObservable, IForwardThrustObservable, IHyperSpaceObservable, ILeftObservable, IRightObservable, IMotionObservable, IQuitObservable, IReverseThrustObservable, IShape, IShieldObservable, IStartObservable, IStopObservable{
	
	private static float TOP = DisplayManager.getDisplayManager();
	private static float SHIP_WIDTH = ConfigurationManager.getConfigManager().getConfiguration().getShipWidth();
	private static float LEFT = DisplayManager.getDisplayManager();
	private static float SHIP_HEIGHT = ConfigurationManager.getConfigManager().getConfiguration().getShipHeight();
	private float shotSpeed = 0.0f;
	private float forwardThrust= 0.0f;
	private float reverseThrust= 0.0f;
	private float friction= 0.0f;
	private float leftright= 0.0f;

	private ArrayList<IShape> ishots =  new ArrayList<IShape>();
	
	public PlayerShip(float nShotSpeed, float nForwardThrust, float nReverseThrust, float nFriction, float nLeftRight, float nX, float nY,  float nRotation,
			Color cColor, IStrategy iStrategy) {
		super(nLeft, nTop, nBottom, nX, nY, nRotation, cColor, iStrategy);
		// TODO Auto-generated constructor stub
		shotSpeed = nShotSpeed;
		forwardThrust = nForwardThrust;
		reverseThrust =nReverseThrust;
		friction=nFriction;
		leftright= nLeftRight;
		
		ForwardThrust.startObserving(this);
		ReverseThrust.startObserving(this);
		Fire.startObserving(this);
		Stop.startObserving(this); 
		Left.startObserving(this);
		Right.startObserving(this);
		HyperSpace.startObserving(this);
		Shield.startObserving(this); 		
		
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		SoundManager.getSoundManager().playFire();
		
		float xa = getMidpointX1X3();
		float xb = getMidpointX1X2();
		float ya = getMidpointY1Y3();
		float yb = getMidpointY1Y2(); 
		
		float xdif = xb - xa;
		float ydif = yb - ya;
		
		float fx = 0.0f;
		float fy = 0.0f;
		
		if(xdif == 0){
			fy += shotSpeed * Math.signum(ydif); 
		}
		else if(ydif == 0)
			fx += shotSpeed * Math.signum(xdif);
		else{
			float fdif = Math.abs(xdif) +Math.abs(ydif);
			fx += shotSpeed * Math.sin(Math.toRadians(90 * xdif/fdif));
			fy += shotSpeed * Math.sin(Math.toRadians(90 * ydif/fdif));
		}
		
		ishots.add(new Shot(xb - 2.0f, yb-2.0f, xb+2.0f, yb+2.0f, fx, fy ));
	}

	@Override
	public void forwardThrust() {
		// TODO Auto-generated method stub
		SoundManager.getSoundManager().playForwardThrust();
		setY(getY() - forwardThrust);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		setX(0.0f);
		setY(0.0f);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
		
		Iterator<IShape> iishots = ishots.iterator();
		while(iishots.hasNext()){
			IShape iishape = iishots.next();
			iishape.draw(g);
		}
		
	}
	
	private float friction(float f){
		f = (f * (1.0f - friction));
		if(Math.abs(f) < friction){
			f=0.0f;
		}
		return f;
	}
	
	@Override
	public float getMinX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMinY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		setX(friction(getX()));
		setY(friction(getY()));
		super.update();
		setRotation(0.0f);
		Iterator<IShape> iiShots = ishots.iterator();
		while(iiShots.hasNext()){
			IShape iiShot = iiShots.next();
			iiShot.update();
		}
	}

	

	@Override
	public void hyperSpace() {
		// TODO Auto-generated method stub
		translateXY(-getMinX() + (float)Math.random() * DisplayManager.getDisplayManager(),
					-getMinY() + (float)Math.random() * DisplayManager.getDisplayManager());
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void left() {
		// TODO Auto-generated method stub
		setRotation(-leftright - (getRotation()/60.0f));
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		setRotation(-leftright - (getRotation()/60.0f));
	}

	public void finalize(){
		ForwardThrust.stopObserving(this);
		ReverseThrust.stopObserving(this);
		Fire.stopObserving(this);
		Stop.stopObserving(this);
		Left.stopObserving(this);
		Right.stopObserving(this);
		
	}

	@Override
	public void reverseThrust() {
		// TODO Auto-generated method stub
		SoundManager.getSoundManager().playReverseThrust();
		setY(getY() + reverseThrust);
	}

	@Override
	public void shield() {
		// TODO Auto-generated method stub
		
	}

}
