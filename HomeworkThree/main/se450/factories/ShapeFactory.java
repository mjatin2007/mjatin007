
package main.se450.factories;

import main.se450.exceptions.BadShapeException;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Circle;
import main.se450.model.Line;
import main.se450.model.Shape;
import main.se450.model.Ship;
import main.se450.model.Square;
import main.se450.model.Triangle;

public class ShapeFactory {
    private ShapeFactory() {
    }

    public static IShape makeShape(String type, float left, float top, float right, float bottom, float x, float y, float rotation, int color, IStrategy iStrategy) throws BadShapeException {
        Shape iShape = null;
        if (type.equals("Circle")) {
            iShape = new Circle(left, top, right, bottom, x, y, rotation, color, iStrategy);
        } else if (type.equals("Square")) {
            iShape = new Square(left, top, right, bottom, x, y, rotation, color, iStrategy);
        } else if (type.equals("Line")) {
            iShape = new Line(left, top, right, bottom, x, y, rotation, color, iStrategy);
        } else if (type.equals("Triangle")) {
            iShape = new Triangle(left, top, right, bottom, x, y, rotation, color, iStrategy);
        } else if (type.equals("Ship")) {
            iShape = new Ship(left, top, right, bottom, x, y, rotation, color, iStrategy);
        } else {
            throw new BadShapeException(type);
        }
        return iShape;
    }
}

