
package org.junit.internal;

import java.io.PrintStream;

public interface JUnitSystem {
    @Deprecated
    public void exit(int var1);

    public PrintStream out();
}

