
package main.se450.strategies;

import java.awt.Dimension;
import main.se450.collections.Septuplet;
import main.se450.interfaces.IStrategy;

public class ReboundStrategy
implements IStrategy {
    private Dimension dimension = new Dimension(0, 0);

    public ReboundStrategy(Dimension newDimension) {
        this.dimension.setSize(newDimension);
    }

    @Override
    public Septuplet<?, ?, ?, ?, ?, ?, ?> execute(float left, float top, float right, float bottom, float x, float y, int color) {
        if (left < 0.0f || (double)right > this.dimension.getWidth()) {
            x = - x;
        }
        if (top < 0.0f || (double)bottom > this.dimension.getHeight()) {
            y = - y;
        }
        return new Septuplet<Float, Float, Float, Float, Float, Float, Integer>(Float.valueOf(left), Float.valueOf(top), Float.valueOf(right), Float.valueOf(bottom), Float.valueOf(x), Float.valueOf(y), color);
    }
}

