package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;

import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;

public abstract class Shape implements IShape
{
	private float     x1        = 0.0f;
	private float     y1        = 0.0f;
	private float     x2        = 0.0f;
	private float     y2        = 0.0f;
	private float     x3        = 0.0f;
	private float     y3        = 0.0f;
	private float     x4        = 0.0f;
	private float     y4        = 0.0f;
	private float     x         = 0.0f;
	private float     y         = 0.0f;
	private float     rotation  = 0.0f;
	private Color     color     = null;
	private IStrategy iStrategy = null;
	
	
	
	//Read only pattern
	public Shape(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, final IStrategy iiStrategy)
	{
		x1        = nLeft;
		y1        = nTop;
		x2        = nRight;
		y2        = nTop;
		x3        = nRight;
		y3        = nBottom;
		x4        = nLeft;
		y4        = nBottom;
		x         = nX;
		y         = nY;
		rotation  = nRotation;
		color     = cColor;
		iStrategy = iiStrategy;
	}
	
	public float getX1()
	{
		return x1;
	}
	
	public float getY1()
	{
		return y1;
	}
	
	public float getX2()
	{
		return x2;
	}
	
	public float getY2()
	{
		return y2;
	}
	
	public float getX3()
	{
		return x3;
	}
	
	public float getY3()
	{
		return y3;
	}
	
	public float getX4()
	{
		return x4;
	}
	
	public float getY4()
	{
		return y4;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getRotation()
	{
		return rotation;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public final IStrategy getStrategy()
	{
		return iStrategy;
	}
	
	public float getWidth()
	{
		return (float) (Math.sqrt(Math.pow(x3 - x1, 2.0) + Math.pow(y3 - y1, 2.0)));
	}
	
	public float getHeight()
	{
		return (float) (Math.sqrt(Math.pow(x4 - x2, 2.0) + Math.pow(y4 - y2, 2.0)));
	}
	
	public void setX1(float nX1)
	{
		x1  = nX1;
	}
	
	public void setY1(float nY1)
	{
		y1  = nY1;
	}
	
	public void setX2(float nX2)
	{
		x2 = nX2;
	}
	
	public void setY2(float nY2)
	{
		y2 = nY2;
	}
	
	public void setX3(float nX3)
	{
		x3 = nX3;
	}
	
	public void setY3(float nY3)
	{
		y3 = nY3;
	}
	
	public void setX4(float nX4)
	{
		x4 = nX4;
	}
	
	public void setY4(float nY4)
	{
		y4 = nY4;
	}
	
	public void setX(float nX)
	{
		x = nX;
	}
	
	public void setY(float nY)
	{
		y = nY;
	}
	
	public void setRotation(float nRotation)
	{
		rotation = nRotation;
	}
	
	public void setColor(Color cColor)
	{
		color = cColor;
	}
	
	public void setStrategy(final IStrategy iiStrategy)
	{
		iStrategy = iiStrategy;
	}

    @Override
    public void update()
    {
    	transform();
    	
    	iStrategy.execute(this);    	
    }
    
	@Override
	public abstract void draw(Graphics g);
	
	@Override
	abstract public float getMinX();

	@Override
	abstract public float getMinY();
	
	@Override
	abstract public float getMaxX();
	
	@Override
	abstract public float getMaxY();
	
	public void translateX(float nX)
	{
		x1 += nX;
		x2 += nX;
		x3 += nX;
		x4 += nX;
	}
	
	public void translateY(float nY)
	{
		y1 += nY;
		y2 += nY;
		y3 += nY;
		y4 += nY;		
	}
	
	public void translateXY(float nX, float nY)
	{
		translateX(nX);
		translateY(nY);
	}

	private void transform() 
	{		
		//translate first
		translateXY(x,y);
		
		float midX = getMidpointX1X3();
		float midY = getMidpointY1Y3();
		
		translateXY(-midX,-midY);
		
		// then rotate
		float radians = (float) Math.toRadians(rotation);
		float sinR    = (float) Math.sin(radians);
		float cosR    = (float) Math.cos(radians);
		
		float xPrime1 = (float) ((x1 * cosR) - (y1 * sinR));
		float yPrime1 = (float) ((y1 * cosR) + (x1 * sinR));
		
		float xPrime2 = (float) ((x2 * cosR) - (y2 * sinR));
		float yPrime2 = (float) ((y2 * cosR) + (x2 * sinR));
		
		float xPrime3 = (float) ((x3 * cosR) - (y3 * sinR));
		float yPrime3 = (float) ((y3 * cosR) + (x3 * sinR));
		
		float xPrime4 = (float) ((x4 * cosR) - (y4 * sinR));
		float yPrime4 = (float) ((y4 * cosR) + (x4 * sinR));
		
		x1 = midX + xPrime1;
		x2 = midX + xPrime2;
		x3 = midX + xPrime3;
		x4 = midX + xPrime4;
		y1 = midY + yPrime1;
		y2 = midY + yPrime2;
		y3 = midY + yPrime3;
		y4 = midY + yPrime4;
	}	

	public float getMidpointX1X3()
	{
		return ((x1 + x3) * 0.5f);
	}
	
	public float getMidpointY1Y3()
	{
		return ((y1 + y3) * 0.5f);
	}
}
      