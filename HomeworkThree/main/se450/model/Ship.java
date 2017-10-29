
package main.se450.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import main.se450.interfaces.IStrategy;
import main.se450.model.Line;
import main.se450.model.Shape;

public class Ship
extends Shape {
    private ArrayList<Line> sides = new ArrayList(4);
    private static final int SIDE_ONE = 0;
    private static final int SIDE_TWO = 1;
    private static final int SIDE_THREE = 2;
    private static final int SIDE_FOUR = 3;

    public Ship(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, int nColor, IStrategy iStrategy) {
        super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, nColor, iStrategy);
        this.createLines();
    }

    @Override
    public void move(float x, float y) {
        super.move(x, y);
        this.createLines();
    }

    @Override
    public void move() {
        super.move();
        this.createLines();
    }

    @Override
    public void strategicmove() {
        super.strategicmove();
        this.createLines();
    }

    private void createLines() {
        this.sides.clear();
        this.sides.add(0, new Line(this.getLeft(), this.getBottom(), this.getMidpointX(), this.getTop(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
        this.sides.add(1, new Line(this.getLeft(), this.getBottom(), this.getMidpointX(), this.getMidpointY(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
        this.sides.add(2, new Line(this.getMidpointX(), this.getMidpointY(), this.getRight(), this.getBottom(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
        this.sides.add(3, new Line(this.getMidpointX(), this.getTop(), this.getRight(), this.getBottom(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
    }

    public float getMidpointX() {
        return (this.getRight() + this.getLeft()) * 0.5f;
    }

    public float getMidpointY() {
        return (this.getTop() + this.getBottom()) * 0.5f;
    }

    @Override
    public void draw(Graphics graphics) {
        Iterator<Line> iSides = this.sides.iterator();
        while (iSides.hasNext()) {
            iSides.next().draw(graphics);
        }
    }
}

