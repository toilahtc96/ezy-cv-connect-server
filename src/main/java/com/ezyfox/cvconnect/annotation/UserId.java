package com.ezyfox.cvconnect.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface UserId {
}
