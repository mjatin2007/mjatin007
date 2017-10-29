
package main.se450.model;

import java.awt.Graphics;
import main.se450.collections.Septuplet;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;

public abstract class Shape
implements IShape {
    private float left = 0.0f;
    private float top = 0.0f;
    private float right = 0.0f;
    private float bottom = 0.0f;
    private float x = 0.0f;
    private float y = 0.0f;
    private float rotation = 0.0f;
    private int color = 0;
    private IStrategy iStrategy = null;

    public Shape(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, int nColor, IStrategy iiStrategy) {
        this.left = nLeft;
        this.top = nTop;
        this.right = nRight;
        this.bottom = nBottom;
        this.x = nX;
        this.y = nY;
        this.rotation = nRotation;
        this.color = nColor;
        this.iStrategy = iiStrategy;
    }

    public float getLeft() {
        return this.left;
    }

    public float getTop() {
        return this.top;
    }

    public float getRight() {
        return this.right;
    }

    public float getBottom() {
        return this.bottom;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRotation() {
        return this.rotation;
    }

    public int getColor() {
        return this.color;
    }

    public float getWidth() {
        return this.getRight() - this.getLeft();
    }

    public float getHeight() {
        return this.getBottom() - this.getTop();
    }

    public final IStrategy getStrategy() {
        return this.iStrategy;
    }

    @Override
    public void move() {
        this.left += this.x;
        this.top += this.y;
        this.right += this.x;
        this.bottom += this.y;
    }

    @Override
    public void move(float nX, float nY) {
        this.left += nX;
        this.top += nY;
        this.right += nX;
        this.bottom += nY;
    }

    @Override
    public void strategicmove() {
        this.move();
        Septuplet septuplet = this.iStrategy.execute(this.left, this.top, this.right, this.bottom, this.x, this.y, this.color);
        this.left = ((Float)septuplet.getT1()).floatValue();
        this.top = ((Float)septuplet.getT2()).floatValue();
        this.right = ((Float)septuplet.getT3()).floatValue();
        this.bottom = ((Float)septuplet.getT4()).floatValue();
        this.x = ((Float)septuplet.getT5()).floatValue();
        this.y = ((Float)septuplet.getT6()).floatValue();
        this.color = (Integer)septuplet.getT7();
    }

    @Override
    public abstract void draw(Graphics var1);
}

