
package main.se450.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import main.se450.exceptions.BadShapeException;
import main.se450.factories.JSONFileShapeListFactory;
import main.se450.gui.JShapePanel;
import main.se450.interfaces.IShape;
import main.se450.singletons.ShapeList;

public class HomeworkThree
extends JFrame {
    private static final long serialVersionUID = 1;
    private Container content;
    private JPanel movePanel;
    private JButton startButton;
    private JButton stopButton;
    private JShapePanel shapeOutput;

    public HomeworkThree() {
        this.setSize(1200, 1600);
        this.setTitle("Homework Three");
        this.setResizable(false);
        this.movePanel = new JPanel();
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        Font bigFont = new Font("Arial", 1, 36);
        this.startButton.setFont(bigFont);
        this.stopButton.setFont(bigFont);
        this.startButton.setPreferredSize(new Dimension(480, 200));
        this.stopButton.setPreferredSize(new Dimension(480, 200));
        SpringLayout movePanelLayout = new SpringLayout();
        movePanelLayout.putConstraint("North", (Component)this.startButton, 5, "North", (Component)this.movePanel);
        movePanelLayout.putConstraint("South", (Component)this.movePanel, 10, "South", (Component)this.startButton);
        movePanelLayout.putConstraint("North", (Component)this.stopButton, 0, "North", (Component)this.startButton);
        movePanelLayout.putConstraint("West", (Component)this.startButton, 15, "West", (Component)this.movePanel);
        movePanelLayout.putConstraint("East", (Component)this.stopButton, -15, "East", (Component)this.movePanel);
        this.movePanel.setLayout(movePanelLayout);
        this.movePanel.add(this.startButton);
        this.movePanel.add(this.stopButton);
        this.shapeOutput = new JShapePanel();
        this.movePanel.setBorder(new TitledBorder(new EtchedBorder(), "Observe"));
        this.content = this.getContentPane();
        this.content.add(this.movePanel);
        this.content.add(this.shapeOutput);
        SpringLayout contentLayout = new SpringLayout();
        contentLayout.putConstraint("West", (Component)this.movePanel, 20, "West", (Component)this.content);
        contentLayout.putConstraint("East", (Component)this.movePanel, -20, "East", (Component)this.content);
        contentLayout.putConstraint("North", (Component)this.content, 10, "South", (Component)this.movePanel);
        contentLayout.putConstraint("North", (Component)this.shapeOutput, 100, "South", (Component)this.startButton);
        contentLayout.putConstraint("South", (Component)this.shapeOutput, -10, "South", (Component)this.content);
        contentLayout.putConstraint("West", (Component)this.shapeOutput, 30, "West", (Component)this.content);
        contentLayout.putConstraint("East", (Component)this.shapeOutput, -30, "East", (Component)this.content);
        this.content.setLayout(contentLayout);
        this.setDefaultCloseOperation(3);
        this.startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                HomeworkThree.this.shapeOutput.startObserving();
            }
        });
        this.stopButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                HomeworkThree.this.shapeOutput.stopObserving();
            }
        });
        this.setVisible(true);
        ArrayList<IShape> iShapes = JSONFileShapeListFactory.makeShape("shapes.json", this.shapeOutput.getSize());
        ShapeList.getShapeList().addShapes(iShapes);
    }

    public HomeworkThree getHomeworkThree() {
        return this;
    }

    public static void main(String[] args) throws BadShapeException {
        new main.se450.main.HomeworkThree();
    }

}

