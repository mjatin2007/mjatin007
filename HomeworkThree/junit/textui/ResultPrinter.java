
package junit.textui;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Enumeration;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.runner.BaseTestRunner;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResultPrinter
implements TestListener {
    PrintStream fWriter;
    int fColumn = 0;

    public ResultPrinter(PrintStream writer) {
        this.fWriter = writer;
    }

    synchronized void print(TestResult result, long runTime) {
        this.printHeader(runTime);
        this.printErrors(result);
        this.printFailures(result);
        this.printFooter(result);
    }

    void printWaitPrompt() {
        this.getWriter().println();
        this.getWriter().println("<RETURN> to continue");
    }

    protected void printHeader(long runTime) {
        this.getWriter().println();
        this.getWriter().println("Time: " + this.elapsedTimeAsString(runTime));
    }

    protected void printErrors(TestResult result) {
        this.printDefects(result.errors(), result.errorCount(), "error");
    }

    protected void printFailures(TestResult result) {
        this.printDefects(result.failures(), result.failureCount(), "failure");
    }

    protected void printDefects(Enumeration<TestFailure> booBoos, int count, String type) {
        if (count == 0) {
            return;
        }
        if (count == 1) {
            this.getWriter().println("There was " + count + " " + type + ":");
        } else {
            this.getWriter().println("There were " + count + " " + type + "s:");
        }
        int i = 1;
        while (booBoos.hasMoreElements()) {
            this.printDefect(booBoos.nextElement(), i);
            ++i;
        }
    }

    public void printDefect(TestFailure booBoo, int count) {
        this.printDefectHeader(booBoo, count);
        this.printDefectTrace(booBoo);
    }

    protected void printDefectHeader(TestFailure booBoo, int count) {
        this.getWriter().print("" + count + ") " + booBoo.failedTest());
    }

    protected void printDefectTrace(TestFailure booBoo) {
        this.getWriter().print(BaseTestRunner.getFilteredTrace(booBoo.trace()));
    }

    protected void printFooter(TestResult result) {
        if (result.wasSuccessful()) {
            this.getWriter().println();
            this.getWriter().print("OK");
            this.getWriter().println(" (" + result.runCount() + " test" + (result.runCount() == 1 ? "" : "s") + ")");
        } else {
            this.getWriter().println();
            this.getWriter().println("FAILURES!!!");
            this.getWriter().println("Tests run: " + result.runCount() + ",  Failures: " + result.failureCount() + ",  Errors: " + result.errorCount());
        }
        this.getWriter().println();
    }

    protected String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double)runTime / 1000.0);
    }

    public PrintStream getWriter() {
        return this.fWriter;
    }

    @Override
    public void addError(Test test, Throwable e) {
        this.getWriter().print("E");
    }

    @Override
    public void addFailure(Test test, AssertionFailedError t) {
        this.getWriter().print("F");
    }

    @Override
    public void endTest(Test test) {
    }

    @Override
    public void startTest(Test test) {
        this.getWriter().print(".");
        if (this.fColumn++ >= 40) {
            this.getWriter().println();
            this.fColumn = 0;
        }
    }
}

