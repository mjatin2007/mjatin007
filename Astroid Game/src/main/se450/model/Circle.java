package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import main.se450.interfaces.IStrategy;

public class Circle extends Shape
{
	private Line2D    line   = new Line2D.Float(0.0f,0.0f,0.0f,0.0f);
	private	Ellipse2D circle = new Ellipse2D.Float(0.0f,0.0f,0.0f,0.0f);
		
	public Circle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
	}
	
	@Override
	public void draw(Graphics graphics) 
	{
		line.setLine(getCenterX(), getCenterY(), getX1(), getY1());
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
  		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(circle);
  		g2d.draw(line);
	}

	@Override
	public float getMinX() 
	{
		return getCenterX() - getRadius();
	}

	@Override
	public float getMinY() 
	{
		return getCenterY() - getRadius();
	}

	@Override
	public float getMaxX() 
	{
		return getCenterX() + getRadius();
	}

	@Override
	public float getMaxY() 
	{
		return getCenterY() + getRadius();
	}
	
	public float getCenterX()
	{
		return getMidpointX1X3();
	}

	public float getCenterY()
	{
		return getMidpointY1Y3();
	}

	public float getRadius()
	{
		return getWidth() * 0.5f; //getWidth == getHeight for circle
	}
}
      