
package org.junit.experimental.categories;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.experimental.categories.CategoryValidator;
import org.junit.validator.ValidateWith;

@Retention(value=RetentionPolicy.RUNTIME)
@Inherited
@ValidateWith(value=CategoryValidator.class)
public @interface Category {
    public Class<?>[] value();
}

