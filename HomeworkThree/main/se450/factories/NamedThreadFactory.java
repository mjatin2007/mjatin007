
package main.se450.factories;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory
implements ThreadFactory {
    private String threadName = null;

    public NamedThreadFactory(String sThreadName) {
        this.threadName = sThreadName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.threadName);
    }

    public final String getThreadName() {
        return this.threadName;
    }
}

