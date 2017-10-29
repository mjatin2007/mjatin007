
package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import main.se450.interfaces.IMotionObservable;
import main.se450.interfaces.IShape;
import main.se450.observable.Motion;
import main.se450.singletons.ShapeList;

public class JShapePanel
extends JPanel
implements IMotionObservable {
    private static final long serialVersionUID = 1;

    @Override
    public void paint(Graphics g) {
        Dimension dimension = this.getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, dimension.width, dimension.height);
        ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
        if (iShapeList != null) {
            Iterator<IShape> iiShapes = iShapeList.iterator();
            while (iiShapes.hasNext()) {
                iiShapes.next().draw(g);
            }
        }
    }

    @Override
    public void update() {
        ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
        if (iShapeList != null) {
            for (IShape iShape : iShapeList) {
                if (iShape == null) continue;
                iShape.strategicmove();
            }
        }
        this.repaint();
    }

    public void startObserving() {
        Motion.startObserving(this);
    }

    public void stopObserving() {
        Motion.stopObserving(this);
    }
}

