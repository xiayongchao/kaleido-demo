package org.jc.framework.kaleido.starter;


import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.annotation.KaleidoComponentScan;
import org.jc.framework.kaleido.annotation.KaleidoConverter;
import org.jc.framework.kaleido.annotation.KaleidoInstancer;
import org.jc.framework.kaleido.converter.AbstractConverter;
import org.jc.framework.kaleido.definition.ConverterBeanDefinition;
import org.jc.framework.kaleido.definition.InstancerBeanDefinition;
import org.jc.framework.kaleido.definition.KaleidoComponentScanDefinition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.AbstractInstancer;
import org.jc.framework.kaleido.util.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiayc
 * @date 2019/3/25
 */
public class KaleidoMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor, InitializingBean, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
    private final Kaleidoscope kaleidoscope;
    private final ConverterBeanDefinitionScanParser converterBeanDefinitionScanParser;
    private final InstancerBeanDefinitionScanParser instancerBeanDefinitionScanParser;
    private final Set<String> kaleidoBeans = new HashSet<>();

    public KaleidoMergedBeanDefinitionPostProcessor(Kaleidoscope kaleidoscope,
                                                    ConverterBeanDefinitionScanParser converterBeanDefinitionScanParser,
                                                    InstancerBeanDefinitionScanParser instancerBeanDefinitionScanParser) {
        this.kaleidoscope = kaleidoscope;
        this.converterBeanDefinitionScanParser = converterBeanDefinitionScanParser;
        this.instancerBeanDefinitionScanParser = instancerBeanDefinitionScanParser;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
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
        System.out.println("========================afterPropertiesSet");
        System.out.println();
        try {
            Set<KaleidoComponentScanDefinition> kaleidoComponentScanDefinitions = new HashSet<>();
            kaleidoComponentScanDefinitions.add(new KaleidoComponentScanDefinition(new String[]{"org.jc.framework.kaleido"}, "**/*.class"));
            doScanAndRegister(kaleidoComponentScanDefinitions);
        } catch (IOException e) {
            throw new KaleidoException(e, "解析Kaleido Bean Definition异常");
        }
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        System.out.println("++++++++++++++++++=" + beanName);

        Set<KaleidoComponentScanDefinition> kaleidoComponentScanDefinitions = new HashSet<>();
        KaleidoComponentScan kaleidoComponentScan = AnnotationUtils.findAnnotation(beanType, KaleidoComponentScan.class);
        if (kaleidoComponentScan != null
                && ArrayUtils.isNotEmpty(kaleidoComponentScan.basePackages())) {
            kaleidoComponentScanDefinitions.add(new KaleidoComponentScanDefinition(kaleidoComponentScan));
        }
        try {
            doScanAndRegister(kaleidoComponentScanDefinitions);
        } catch (IOException | InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new KaleidoException(e, "解析Kaleido Bean Definition异常");
        }
    }

    private void doScanAndRegister(Set<KaleidoComponentScanDefinition> kaleidoComponentScanDefinitions) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (CollectionUtils.isEmpty(kaleidoComponentScanDefinitions)) {
            return;
        }
        for (KaleidoComponentScanDefinition kaleidoComponentScanDefinition : kaleidoComponentScanDefinitions) {
            Set<ConverterBeanDefinition> converterBeanDefinitions = converterBeanDefinitionScanParser.scanParse(kaleidoBeans, kaleidoComponentScanDefinition);
            Set<InstancerBeanDefinition> instancerBeanDefinitions = instancerBeanDefinitionScanParser.scanParse(kaleidoBeans, kaleidoComponentScanDefinition);
            registerKaleidoBean(converterBeanDefinitions, instancerBeanDefinitions);
        }
    }

    private void registerKaleidoBean(final Set<ConverterBeanDefinition> converterBeanDefinitions,
                                     final Set<InstancerBeanDefinition> instancerBeanDefinitions) throws
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (!CollectionUtils.isEmpty(converterBeanDefinitions)) {
            for (ConverterBeanDefinition converterBeanDefinition : converterBeanDefinitions) {
                registerKaleidoBean(converterBeanDefinition.getBeanName(), converterBeanDefinition.getBeanClassName(), KaleidoConverter.class);
            }
        }
        if (!CollectionUtils.isEmpty(instancerBeanDefinitions)) {
            for (InstancerBeanDefinition instancerBeanDefinition : instancerBeanDefinitions) {
                registerKaleidoBean(instancerBeanDefinition.getBeanName(), instancerBeanDefinition.getBeanClassName(), KaleidoInstancer.class);

            }
        }
    }

    private void registerKaleidoBean(String beanName, String beanClassName, Class<? extends Annotation> annotationClass) throws
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Object object;
        Class beanType = Class.forName(beanClassName, true, this.beanFactory.getBeanClassLoader());
        if (beanType.isEnum() || beanType.isArray() || beanType.isAnnotation()) {
            throw new KaleidoException("[%s]类型上不支持使用注解[@%s]", beanClassName, annotationClass.getName());
        }
        if (this.beanFactory.containsBean(beanName)) {
            throw new KaleidoException("注册@%s Bean[%s]失败,已经存在同名Bean[name=%s]", annotationClass.getName(), beanClassName, beanName);
        }

        object = beanType.getDeclaredConstructor().newInstance();
        if (KaleidoConverter.class.equals(annotationClass)) {
            kaleidoscope.registerConverter((AbstractConverter) object);
        } else if (KaleidoInstancer.class.equals(annotationClass)) {
            kaleidoscope.registerInstancer((AbstractInstancer) object);
        }

        //将生成的对象注册到Spring容器
        this.beanFactory.registerSingleton(beanName, object);
    }
}
