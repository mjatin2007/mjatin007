package main.se450.model;

import java.awt.Color;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IStrategy;

public class Square extends ShapeDroid
{
	public Square(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
    }

    public void addSides(LineCollection lineCollection)
    {
    	if (lineCollection != null)
    	{
	    	lineCollection.add(new Line(getX1(), getY1(), getX2(), getY2(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX2(), getY2(), getX3(), getY3(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX3(), getY3(), getX4(), getY4(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX4(), getY4(), getX1(), getY1(), getX(), getY(), getRotation(), getColor(), getStrategy()));
    	}
    }
}
    