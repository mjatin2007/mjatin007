
package org.junit.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayComparisonFailure
extends AssertionError {
    private static final long serialVersionUID = 1;
    private final List<Integer> fIndices = new ArrayList<Integer>();
    private final String fMessage;

    public ArrayComparisonFailure(String message, AssertionError cause, int index) {
        this.fMessage = message;
        this.initCause((Throwable)((Object)cause));
        this.addDimension(index);
    }

    public void addDimension(int index) {
        this.fIndices.add(0, index);
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        if (this.fMessage != null) {
            sb.append(this.fMessage);
        }
        sb.append("arrays first differed at element ");
        Iterator<Integer> i$ = this.fIndices.iterator();
        while (i$.hasNext()) {
            int each = i$.next();
            sb.append("[");
            sb.append(each);
            sb.append("]");
        }
        sb.append("; ");
        sb.append(this.getCause().getMessage());
        return sb.toString();
    }

    public String toString() {
        return this.getMessage();
    }
}

