
package main.se450.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShapeParser {
    public static ArrayList<String> getShapes(String fileName) {
        ArrayList<String> strings = new ArrayList<String>();
        try {
            JSONParser parser3 = new JSONParser();
            Object obj = parser3.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray jsonArray = (JSONArray)jsonObject.get("shapes");
            for (JSONObject someShape : jsonArray) {
                if (!someShape.containsKey("type")) continue;
                strings.add(someShape.toString());
            }
        }
        catch (FileNotFoundException parser3) {
        }
        catch (IOException parser3) {
        }
        catch (ParseException parser3) {
            // empty catch block
        }
        return strings;
    }
}

