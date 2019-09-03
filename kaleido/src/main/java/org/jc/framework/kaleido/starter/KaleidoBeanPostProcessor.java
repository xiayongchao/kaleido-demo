package org.jc.framework.kaleido.starter;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.converter.AbstractConverter;
import org.jc.framework.kaleido.instancer.AbstractInstancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author xiayc
 * @date 2019/5/8
 */
public class KaleidoBeanPostProcessor implements BeanPostProcessor, InitializingBean, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
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
        System.out.println(beanName);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
        System.out.println("++++++++++++++++++afterPropertiesSet");
        System.out.println();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
