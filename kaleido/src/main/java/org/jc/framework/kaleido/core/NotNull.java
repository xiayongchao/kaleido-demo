package org.jc.framework.kaleido.core;

import java.lang.annotation.*;

/**
 * 仅仅标记被注解的元素不为NULL
 * 不进行校验，也不严格保证确实不为NULL
 *
 * @author xiayc
 * @date 2019/5/9
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {
}
