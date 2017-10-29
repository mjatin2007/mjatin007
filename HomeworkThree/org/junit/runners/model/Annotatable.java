
package org.junit.runners.model;

import java.lang.annotation.Annotation;

public interface Annotatable {
    public Annotation[] getAnnotations();

    public <T extends Annotation> T getAnnotation(Class<T> var1);
}

