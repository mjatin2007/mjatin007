
package main.se450.singletons;

import java.util.ArrayList;
import java.util.Collection;
import main.se450.interfaces.IShape;

public class ShapeList {
    private static ShapeList shapeList = null;
    private ArrayList<IShape> iShapes = new ArrayList();

    static {
        shapeList = new ShapeList();
    }

    private ShapeList() {
    }

    public static final ShapeList getShapeList() {
        return shapeList;
    }

    public final synchronized ArrayList<IShape> getShapes() {
        return this.iShapes;
    }

    public synchronized void addShapes(ArrayList<IShape> iShapeList) {
        this.iShapes.addAll(iShapeList);
    }
}

