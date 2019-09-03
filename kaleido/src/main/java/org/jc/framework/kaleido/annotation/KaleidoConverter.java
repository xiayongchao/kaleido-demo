package org.jc.framework.kaleido.annotation;

import org.jc.framework.kaleido.util.Strings;

import java.lang.annotation.*;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KaleidoConverter {
    /**
     * Bean名称，用于Spring注入
     *
     * @return
     */
    String value() default Strings.EMPTY_STRING;
}
