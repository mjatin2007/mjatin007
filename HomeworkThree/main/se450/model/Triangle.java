
package main.se450.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Line;
import main.se450.model.Shape;

public class Triangle
extends Shape {
    private ArrayList<IShape> sides = new ArrayList(3);
    private static final int BOTTOM_SIDE = 0;
    private static final int LEFT_SIDE = 1;
    private static final int RIGHT_SIDE = 2;

    public Triangle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, int nColor, IStrategy iStrategy) {
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
        this.sides.add(0, new Line(this.getLeft(), this.getBottom(), this.getRight(), this.getBottom(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
        this.sides.add(1, new Line(this.getLeft(), this.getBottom(), this.getMidpoint(), this.getTop(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
        this.sides.add(2, new Line(this.getMidpoint(), this.getTop(), this.getRight(), this.getBottom(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy()));
    }

    private float getMidpoint() {
        return (this.getRight() + this.getLeft()) * 0.5f;
    }

    @Override
    public void draw(Graphics graphics) {
        Iterator<IShape> iSides = this.sides.iterator();
        while (iSides.hasNext()) {
            iSides.next().draw(graphics);
        }
    }
}

