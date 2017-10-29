
package main.se450.factories;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.BadStrategyException;
import main.se450.factories.ShapeFactory;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.utilities.Extractor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileShapeListFactory {
    private JSONFileShapeListFactory() {
    }

    public static ArrayList<IShape> makeShape(String fileName, Dimension dimension) {
        ArrayList<IShape> iShapes = new ArrayList<IShape>();
        try {
            JSONParser parser3 = new JSONParser();
            Object obj = parser3.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray jsonArray = (JSONArray)jsonObject.get("shapes");
            for (JSONObject someShape : jsonArray) {
                if (!someShape.containsKey("type")) continue;
                try {
                    try {
                        IStrategy iStrategy = StrategyFactory.makeStrategy(someShape.get("borders").toString(), dimension);
                        IShape iShape = ShapeFactory.makeShape(someShape.get("type").toString(), Extractor.extractFloat(someShape.get("left")).floatValue(), Extractor.extractFloat(someShape.get("top")).floatValue(), Extractor.extractFloat(someShape.get("right")).floatValue(), Extractor.extractFloat(someShape.get("bottom")).floatValue(), Extractor.extractFloat(someShape.get("x")).floatValue(), Extractor.extractFloat(someShape.get("y")).floatValue(), Extractor.extractFloat(someShape.get("rotation")).floatValue(), Extractor.extractInteger(someShape.get("color")), iStrategy);
                        iShapes.add(iShape);
                    }
                    catch (BadStrategyException e) {
                        System.out.println(e);
                    }
                    continue;
                }
                catch (BadShapeException e) {
                    System.out.println(e);
                }
            }
        }
        catch (FileNotFoundException parser3) {
        }
        catch (IOException parser3) {
        }
        catch (ParseException parser3) {
            // empty catch block
        }
        return iShapes;
    }
}

