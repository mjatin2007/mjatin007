
package org.junit.runner;

import java.util.Comparator;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.internal.requests.ClassRequest;
import org.junit.internal.requests.FilterRequest;
import org.junit.internal.requests.SortingRequest;
import org.junit.internal.runners.ErrorReportingRunner;
import org.junit.runner.Computer;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Request {
    public static Request method(Class<?> clazz, String methodName) {
        Description method = Description.createTestDescription(clazz, methodName);
        return Request.aClass(clazz).filterWith(method);
    }

    public static Request aClass(Class<?> clazz) {
        return new ClassRequest(clazz);
    }

    public static Request classWithoutSuiteMethod(Class<?> clazz) {
        return new ClassRequest(clazz, false);
    }

    public static /* varargs */ Request classes(Computer computer, Class<?> ... classes) {
        try {
            AllDefaultPossibilitiesBuilder builder = new AllDefaultPossibilitiesBuilder(true);
            Runner suite = computer.getSuite(builder, classes);
            return Request.runner(suite);
        }
        catch (InitializationError e) {
            throw new RuntimeException("Bug in saff's brain: Suite constructor, called as above, should always complete");
        }
    }

    public static /* varargs */ Request classes(Class<?> ... classes) {
        return Request.classes(JUnitCore.defaultComputer(), classes);
    }

    public static Request errorReport(Class<?> klass, Throwable cause) {
        return Request.runner(new ErrorReportingRunner(klass, cause));
    }

    public static Request runner(final Runner runner) {
        return new Request(){

            public Runner getRunner() {
                return runner;
            }
        };
    }

    public abstract Runner getRunner();

    public Request filterWith(Filter filter) {
        return new FilterRequest(this, filter);
    }

    public Request filterWith(Description desiredDescription) {
        return this.filterWith(Filter.matchMethodDescription(desiredDescription));
    }

    public Request sortWith(Comparator<Description> comparator) {
        return new SortingRequest(this, comparator);
    }

}

