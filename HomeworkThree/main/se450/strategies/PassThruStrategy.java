
package main.se450.strategies;

import java.awt.Dimension;
import main.se450.collections.Septuplet;
import main.se450.interfaces.IStrategy;

public class PassThruStrategy
implements IStrategy {
    private Dimension dimension = new Dimension();

    public PassThruStrategy(Dimension newDimension) {
        this.dimension.setSize(newDimension);
    }

    @Override
    public Septuplet<?, ?, ?, ?, ?, ?, ?> execute(float left, float top, float right, float bottom, float x, float y, int color) {
        float nHeight;
        float nWidth;
        if (right < 0.0f) {
            nWidth = right - left;
            left = this.dimension.width;
            right = left + nWidth;
        } else if ((double)left > this.dimension.getWidth()) {
            nWidth = right - left;
            right = 0.0f;
            left = right - nWidth;
        }
        if (bottom < 0.0f) {
            nHeight = bottom - top;
            top = this.dimension.height;
            bottom = top + nHeight;
        } else if ((double)top > this.dimension.getHeight()) {
            nHeight = bottom - top;
            bottom = 0.0f;
            top = bottom - nHeight;
        }
        return new Septuplet<Float, Float, Float, Float, Float, Float, Integer>(Float.valueOf(left), Float.valueOf(top), Float.valueOf(right), Float.valueOf(bottom), Float.valueOf(x), Float.valueOf(y), color);
    }
}

