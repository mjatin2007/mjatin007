
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

public interface Matcher<T>
extends SelfDescribing {
    public boolean matches(Object var1);

    public void describeMismatch(Object var1, Description var2);

    @Deprecated
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_();
}

