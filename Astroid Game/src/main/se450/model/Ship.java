package main.se450.model;

import java.awt.Color;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IStrategy;

public class Ship extends ShapeDroid
{
	public Ship(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
    }

    public void addSides(LineCollection lineCollection)
    {
    	if (lineCollection != null)
    	{
	        lineCollection.add(new Line(getX4(),           getY4(),           getMidpointX1X2(), getMidpointY1Y2(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getMidpointX1X2(), getMidpointY1Y2(), getX3(),           getY3(), 			getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX3(),           getY3(),           getMidpointX1X3(), getMidpointY1Y3(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getMidpointX1X3(), getMidpointY1Y3(), getX4(),  	     getY4(),        	getX(), getY(), getRotation(), getColor(), getStrategy()));
    	}
    }
	
	public float getMidpointX1X2()
	{
		return ((getX1() + getX2()) * 0.5f);
	}
	
	public float getMidpointY1Y2()
	{
		return ((getY1() + getY2()) * 0.5f);
	}
}
    