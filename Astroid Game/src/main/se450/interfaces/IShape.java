package main.se450.interfaces;

import java.awt.Graphics;

public interface IShape 
{
	void update();
	
	void draw(Graphics g);
	
	float getMinX();
	
	float getMinY();
	
	float getMaxX();
	
	float getMaxY();
}
      