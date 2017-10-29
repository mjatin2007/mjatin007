
package org.junit.experimental.theories;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.experimental.theories.ParameterSupplier;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface ParametersSuppliedBy {
    public Class<? extends ParameterSupplier> value();
}

