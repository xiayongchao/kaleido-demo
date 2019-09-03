package org.jc.framework.kaleido.starter;


import org.jc.framework.kaleido.annotation.KaleidoInstancer;
import org.jc.framework.kaleido.definition.InstancerBeanDefinition;
import org.jc.framework.kaleido.util.Strings;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author xiayc
 * @date 2019/3/25
 */
public class InstancerBeanDefinitionScanParser extends KaleidoBeanDefinitionScanParser<InstancerBeanDefinition> {
    public InstancerBeanDefinitionScanParser() {
        super(KaleidoInstancer.class);
    }

    @Override
    protected InstancerBeanDefinition generateBeanDefinition(final AnnotationMetadata annotationMetadata, final String annotationName, Class<?> sourceClass, Class<?> targetClass) {
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
        if (annotationTypes.isEmpty()) {
            return null;
        }
        String beanName;
        Map<String, Object> map;
        String[] classNameArr;
        for (String annotationType : annotationTypes) {
            if (!annotationType.equals(annotationName)) {
                continue;
            }
            map = annotationMetadata.getAnnotationAttributes(annotationType, false);
            beanName = map.get("value").toString();
            if (!StringUtils.hasText(beanName)) {
                //设置默认值
                classNameArr = annotationMetadata.getClassName().split("\\.");
                beanName = Strings.standingInitialLowercase(classNameArr[classNameArr.length - 1]);
            }

            return new InstancerBeanDefinition(beanName, annotationMetadata.getClassName(), sourceClass, targetClass);
        }
        return null;
    }
}
