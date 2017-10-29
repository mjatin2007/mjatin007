
package main.se450.utilities;

public class Extractor {
    public static final Integer extractInteger(Object object) {
        return Integer.parseInt(object.toString(), 10);
    }

    public static final Float extractFloat(Object object) {
        return Float.valueOf(Float.parseFloat(object.toString()));
    }
}

