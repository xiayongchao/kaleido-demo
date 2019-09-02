package org.jc.framework.kaleido.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TypeRecognition {
    Class<?> sourceClass() default Null.class;

    Class<?> targetClass() default Null.class;
}
