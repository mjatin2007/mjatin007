package main.se450.interfaces;

import java.awt.Graphics;

import main.se450.collections.LineCollection;

public interface IShot {
	 boolean isExpired();
	void update();
	void draw (Graphics g);
	LineCollection getLineCollection();
}
