
package org.junit.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MethodSorter {
    public static final Comparator<Method> DEFAULT = new Comparator<Method>(){

        @Override
        public int compare(Method m1, Method m2) {
            int i2;
            int i1 = m1.getName().hashCode();
            if (i1 != (i2 = m2.getName().hashCode())) {
                return i1 < i2 ? -1 : 1;
            }
            return MethodSorter.NAME_ASCENDING.compare(m1, m2);
        }
    };
    public static final Comparator<Method> NAME_ASCENDING = new Comparator<Method>(){

        @Override
        public int compare(Method m1, Method m2) {
            int comparison = m1.getName().compareTo(m2.getName());
            if (comparison != 0) {
                return comparison;
            }
            return m1.toString().compareTo(m2.toString());
        }
    };

    public static Method[] getDeclaredMethods(Class<?> clazz) {
        Comparator<Method> comparator = MethodSorter.getSorter(clazz.getAnnotation(FixMethodOrder.class));
        Method[] methods = clazz.getDeclaredMethods();
        if (comparator != null) {
            Arrays.sort(methods, comparator);
        }
        return methods;
    }

    private MethodSorter() {
    }

    private static Comparator<Method> getSorter(FixMethodOrder fixMethodOrder) {
        if (fixMethodOrder == null) {
            return DEFAULT;
        }
        return fixMethodOrder.value().getComparator();
    }

}

