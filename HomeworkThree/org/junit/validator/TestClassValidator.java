
package org.junit.validator;

import java.util.List;
import org.junit.runners.model.TestClass;

public interface TestClassValidator {
    public List<Exception> validateTestClass(TestClass var1);
}

