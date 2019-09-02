package org.jc.framework.kaleido.core;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.converter.AbstractConverter;
import org.jc.framework.kaleido.instancer.AbstractInstancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author xiayc
 * @date 2019/5/8
 */
public class KaleidoBeanPostProcessor implements BeanPostProcessor {
    private final Kaleidoscope kaleidoscope;

    public KaleidoBeanPostProcessor(Kaleidoscope kaleidoscope) {
        this.kaleidoscope = kaleidoscope;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractConverter) {
            kaleidoscope.registerConverter((AbstractConverter) bean);
        }
        if (bean instanceof AbstractInstancer) {
            kaleidoscope.registerInstancer((AbstractInstancer) bean);
        }
        return bean;
    }
}
