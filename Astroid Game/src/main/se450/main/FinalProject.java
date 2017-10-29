package main.se450.main;
/*
 * Name     : 
 * Depaul#  : 
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class FinalProject
 *
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import main.se450.exceptions.BadShapeException;
import main.se450.factories.JSONFileShapeListFactory;
import main.se450.gui.ShapeDisplay;
import main.se450.interfaces.IMotionObservable;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.keyboard.Keyboard;
import main.se450.observable.Motion;
import main.se450.singletons.ShapeList;

public class FinalProject extends JFrame implements IObservable, IMotionObservable
{
	private static final long serialVersionUID = 1L;//I added this to remove the warning the Eclipse generates

	private Container content;//used for the content pane
	
	private JPanel scorePanel;//contains score / ships
	
	private JTextField score; //score text
	private int fakeScore = 0;//remove this and the IObservable from this class - its for example only
	
	//private JTextField ships;  //ships text
	
	private ShapeDisplay shapeOutput; //the output for the shape data
	
	public FinalProject()
	{
		setSize(1440, 840);//set the size of the window to 1440 x 840
		setTitle("Final Project");//set the applications title
		setResizable(false);
		
		//centers application on desktop / parent container
		Dimension windowSize = getSize();
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

        setLocation(centerPoint.x - windowSize.width / 2, centerPoint.y - windowSize.height / 2);
        
		//Now create a panel to hold the move combo box and move description
		scorePanel = new JPanel();
		
		//create the move buttons
		score = new JTextField("Score");
		//ships  = new JTextField("Ships");
		
		score.setPreferredSize(new Dimension(300, 80));
		score.setFont(score.getFont().deriveFont(72f)); // will only change size to 12pt
		score.setBackground(Color.BLACK);
		score.setForeground(new Color(224,224,224));//very light grey
		
		//ships.setPreferredSize(new Dimension(100, 64));
		
		//the spring layout will handle the move panels controls spacing 
		//when the window is initialized and sized/resized
		SpringLayout movePanelLayout = new SpringLayout();

		//now lets set up the controls and how they will be position
		//in accordance to one another
		
		//start button is 5 below top of panel
		movePanelLayout.putConstraint(SpringLayout.NORTH, score, 5, 
									  SpringLayout.NORTH, scorePanel);
		
		//start button 5 above botton of panel 
		movePanelLayout.putConstraint(SpringLayout.SOUTH, scorePanel, 10, 
									  SpringLayout.SOUTH, score);
		/*
		//start button and stop button align
		movePanelLayout.putConstraint(SpringLayout.NORTH, ships, 0, 
									  SpringLayout.NORTH, score);*/
		
		//up button top adjust horizontally
		movePanelLayout.putConstraint(SpringLayout.WEST, score, 15, 
									  SpringLayout.WEST, scorePanel);

		//down buttons left side with up button left side
		movePanelLayout.putConstraint(SpringLayout.EAST, score, -15, 
									  SpringLayout.EAST, scorePanel);
		
		/*
		//down buttons left side with up button left side
		movePanelLayout.putConstraint(SpringLayout.EAST, ships, -15, 
									  SpringLayout.EAST, movePanel);*/
		
		//the panel dependencies has been configured so here we set it
		scorePanel.setLayout(movePanelLayout);
		
		//add the buttons to the panel
		scorePanel.add(score);
		//movePanel.add(ships);
		
		//create the text area for displaying the shape(s) location
		shapeOutput  = new ShapeDisplay();
		
		//give the panel an nice etched and titled border with the heading of "Observe"
		scorePanel.setBorder(new TitledBorder(new EtchedBorder(), "Score"));
		
		//get the content pane, as it is were we'll place our controls
		content = getContentPane();

		//Attach the move panel to the content pane
		content.add(scorePanel);
		
	    content.add(shapeOutput);
		
	    //lets create a final spring layout to include the other layout
	    //and add the rest of the controls
		SpringLayout contentLayout = new SpringLayout();
		
		//move panel setup
		//set move  panels west border 30 pixels away from content panes west border
		contentLayout.putConstraint(SpringLayout.WEST, scorePanel, 20, 
									SpringLayout.WEST, content);

		//fix move panels east border -30 pixels away from content panes east border
		contentLayout.putConstraint(SpringLayout.EAST, scorePanel, -20, 
									SpringLayout.EAST, content);
		
		//fix move panels north border 10 pixels away from content panes north border
		contentLayout.putConstraint(SpringLayout.NORTH, content, 10, 
									SpringLayout.SOUTH, scorePanel);
		
		//fix scroll panes north border 10 pixels away from down buttons south border
		contentLayout.putConstraint(SpringLayout.NORTH, shapeOutput, 50, 
									SpringLayout.SOUTH, score);
		
		//fix scroll panes south border -10 pixels away from content panes south border
		contentLayout.putConstraint(SpringLayout.SOUTH, shapeOutput, -10, 
									SpringLayout.SOUTH, content);
		
		//fix scroll panes west border 30 pixels away from content panes west border
		contentLayout.putConstraint(SpringLayout.WEST, shapeOutput, 25, 
									SpringLayout.WEST, content);
		
		//fix scroll panes east border -30 pixels away from content panes east border
		contentLayout.putConstraint(SpringLayout.EAST, shapeOutput, -25, 
									SpringLayout.EAST, content);
		
	    //set the content panes layout 
		content.setLayout( contentLayout );
		
		//set the app to exit when the user presses the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		score.setEnabled(false);
		score.setFocusable(false);
		//ships.setEnabled(false);
		//ships.setFocusable(false);
		
		setFocusable(false);
		
		//set the app to be visible
		setVisible(true);
		
		final ArrayList<IShape> iShapes = JSONFileShapeListFactory.makeShape("shapes.json");
		
		ShapeList.getShapeList().addShapes(iShapes);

		Motion.startObserving(shapeOutput);
		
		Motion.startObserving(this);

		Keyboard.bindGameKeys(shapeOutput);		
		
	}
	
	public FinalProject getFinalProject()
	{
		return this;
	}

	//the main entry point for the application
	public static void main(String[] args) throws BadShapeException
	{
		//create a new final project application
		new FinalProject();
	}

	@Override //Example of catching events
	public void update()
	{
		if (++fakeScore % 20 == 0)
		{
			score.setText("Score: " + Integer.toString(fakeScore));
		}
	}
}
      