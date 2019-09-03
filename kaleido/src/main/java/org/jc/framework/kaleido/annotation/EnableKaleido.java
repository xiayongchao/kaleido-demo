package org.jc.framework.kaleido.annotation;

import java.lang.annotation.*;

/**
 * @author jc
 * @date 2019/9/2 0:16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableKaleido {
}
