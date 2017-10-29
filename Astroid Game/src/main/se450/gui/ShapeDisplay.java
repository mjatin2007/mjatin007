package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import main.se450.factories.JSONFileShapeListFactory;
import main.se450.interfaces.IMotionObservable;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStartObservable;
import main.se450.model.Configuration;
import main.se450.model.PlayerShip;
import main.se450.observable.ForwardThrust;
import main.se450.observable.Motion;
import main.se450.observable.Quit;
import main.se450.observable.ReverseThrust;
import main.se450.observable.Start;
import main.se450.observable.Stop;
import main.se450.parser.ConfigParser;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ShapeList;

/*
 * Name     : 
 * Depaul#  : 
 * Class    : SE 450
 * Project  : Final
 * Due Date : 03/13/2017
 *
 * class ShapeDisplay
 *
 */

public class ShapeDisplay extends JPanel implements IObservable, IMotionObservable, IStartObservable
{
  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayerShip playership = null; 
	
	public ShapeDisplay()
	{
		Start.startObserving(this);
		Quit.startObserving(this);
		Stop.startObserving(this);
		ForwardThrust.startObserving(this);
		ReverseThrust.startObserving(this);
	}
	
	private void loadConfiguration(){
		ConfigParser.loadConfiguration("config");
	}
	
	@Override
	public void validateTree()
	{
		super.validateTree();

		Dimension dimension = getSize();
		
		DisplayManager.getDisplayManager().setDisplaySize(dimension.width, dimension.height);
	}
	

	public void paint(Graphics graphics) 
  	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DisplayManager.getDisplayManager().getWidth(), DisplayManager.getDisplayManager().getHeight());
		
		final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
		if (iShapeList != null)
		{
			Iterator<IShape> iiShapes = iShapeList.iterator();
			while (iiShapes.hasNext())
			{
				IShape iShape = iiShapes.next();
				if (iShape != null)
				{
					iShape.update();
					iShape.draw(graphics);
				}
			}
		}
    }  	

	@Override
	public void update() 
	{
		repaint();
	}
	
	public void start()
	{
		loadConfiguration();
		
		placeShip();
		
		loadshapes();
		
		Motion.startObserving(this);
	}

	private void placeShip() {
		// TODO Auto-generated method stub
		ConfigurationManager cm = ConfigurationManager.getConfigManager();
		if(cm!=null){
			Configuration c = cm.getConfiguration();
			if(c!=null){
				Dimension d = getSize();
				float nLeft = (d.getWidth() / 2.0f);
				float nTop = (d.getHeight() / 2.0f);				
				playerShip = new PlayerShip(c.getShotspeed(), c.getForwardthrust(), c.getReversethrust(), c.getFriction(), c.getLeftright(), c.getX(), c.getY(), 
											c.getRotation(), c.getColor(), c.getBorders());
			}
		}
	}

	private void loadshapes() {
		// TODO Auto-generated method stub
		final ArrayList<IShape> is = JSONFileShapeListFactory.makeShape("shapes.json");
		ShapeList.getShapeList().addShapes(is);
	}
}
