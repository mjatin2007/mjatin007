
package org.junit.validator;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.validator.AnnotationValidator;

@Retention(value=RetentionPolicy.RUNTIME)
@Inherited
public @interface ValidateWith {
    public Class<? extends AnnotationValidator> value();
}

