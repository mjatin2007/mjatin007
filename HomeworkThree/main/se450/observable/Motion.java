
package main.se450.observable;

import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import main.se450.factories.NamedThreadFactory;
import main.se450.interfaces.IMotionObservable;

public class Motion
implements Runnable {
    private static Motion motion = new Motion();
    private boolean inMotion = false;
    private ScheduledThreadPoolExecutor scheduler = null;
    private static final int FRAMES_PER_SECOND = 20;
    private static final int NANO_SECONDS_PER_SECOND = 1000000000;
    private ArrayList<IMotionObservable> observables = new ArrayList();

    private Motion() {
    }

    private static Motion getMotion() {
        return motion;
    }

    public static void startObserving(IMotionObservable iObservable) {
        Motion motion = Motion.getMotion();
        if (motion != null) {
            motion.addObserver(iObservable);
        }
    }

    private synchronized void addObserver(IMotionObservable iObservable) {
        if (iObservable != null && !this.observables.contains(iObservable)) {
            this.observables.add(iObservable);
            if (!this.getIsInMotion()) {
                this.startMotion();
            }
        }
    }

    public static void stopObserving(IMotionObservable iObservable) {
        Motion motion = Motion.getMotion();
        if (motion != null) {
            motion.removeObserver(iObservable);
        }
    }

    private synchronized void removeObserver(IMotionObservable iObservable) {
        this.observables.remove(iObservable);
        if (this.observables.isEmpty()) {
            this.stopMotion();
        }
    }

    private synchronized void startMotion() {
        this.setIsInMotion(true);
        if (this.scheduler == null) {
            NamedThreadFactory lectureFourThreadFactory = new NamedThreadFactory("Motion");
            this.scheduler = new ScheduledThreadPoolExecutor(1, lectureFourThreadFactory);
            if (this.scheduler != null) {
                this.scheduler.scheduleAtFixedRate(this, 0, 50000000, TimeUnit.NANOSECONDS);
            }
        }
    }

    private synchronized void stopMotion() {
        this.setIsInMotion(false);
        if (this.scheduler != null) {
            this.scheduler.shutdown();
            this.scheduler = null;
        }
    }

    public static final boolean isInMotion() {
        Motion motion = Motion.getMotion();
        return motion != null ? motion.getIsInMotion() : false;
    }

    public final synchronized boolean getIsInMotion() {
        return this.inMotion;
    }

    private final synchronized void setIsInMotion(boolean bIsInMotion) {
        this.inMotion = bIsInMotion;
    }

    @Override
    public synchronized void run() {
        if (this.observables != null && Motion.isInMotion()) {
            for (IMotionObservable iObservable : this.observables) {
                if (iObservable == null) continue;
                iObservable.update();
            }
        }
    }
}

