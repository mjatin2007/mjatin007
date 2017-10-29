
package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;

public class Line
extends Shape {
    public Line(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, int nColor, IStrategy iStrategy) {
        super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, nColor, iStrategy);
    }

    @Override
    public void draw(Graphics graphics) {
        Line2D.Float line = new Line2D.Float(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(new Color(this.getColor()));
        g2d.draw(line);
    }
}

