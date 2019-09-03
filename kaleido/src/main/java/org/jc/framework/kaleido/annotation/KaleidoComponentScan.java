package org.jc.framework.kaleido.annotation;


import java.lang.annotation.*;

/**
 * @author xiayc
 * @date 2019/8/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KaleidoComponentScan {
    /**
     * 扫描包名列表
     *
     * @return
     */
    String[] basePackages() default {};

    /**
     * 匹配模式
     *
     * @return
     */
    String resourcePattern() default "**/*.class";
}
