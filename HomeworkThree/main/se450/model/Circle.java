
package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;

public class Circle
extends Shape {
    public Circle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, int nColor, IStrategy iStrategy) {
        super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, nColor, iStrategy);
    }

    @Override
    public void draw(Graphics graphics) {
        Ellipse2D.Float circle = new Ellipse2D.Float(this.getLeft(), this.getTop(), this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(new Color(this.getColor()));
        g2d.draw(circle);
    }

    public float getRadius() {
        return (this.getLeft() + this.getRight()) / 2.0f - this.getLeft();
    }

    public final Point2D.Float getCenter() {
        return new Point2D.Float((this.getLeft() + this.getRight()) / 2.0f, (this.getTop() + this.getBottom()) / 2.0f);
    }
}

