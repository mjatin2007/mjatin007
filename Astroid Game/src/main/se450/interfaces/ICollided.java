package main.se450.interfaces;

import main.se450.collections.LineCollection;

public interface ICollided {
	public boolean hasCollided();
	public void collide();
	public void addSides(LineCollection linecollection);
	boolean isExpired();
}
