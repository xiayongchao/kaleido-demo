package org.jc.framework.kaleido.core;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.instancer.InstanceSupport;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jc
 * @date 2019/9/3 23:25
 */
public abstract class InstanceSupporter extends KaleidoscopeSupporter {
    private final Map<String, InstanceSupport<?>> instancerMap = new ConcurrentHashMap<>();

    public void registerInstancer(InstanceSupport instanceSupport) {
        Class<? extends InstanceSupport> instancersClass;
        Type[] actualTypeArguments = ((ParameterizedType) (instancersClass = instanceSupport.getClass()).getGenericSuperclass()).getActualTypeArguments();
        instancerMap.put(getKey(actualTypeArguments[0], AnnotationUtils.findAnnotation(instancersClass, TypeRecognition.class)), instanceSupport);
    }

    public <T> InstanceSupport<T> getInstancer(Class<T> tClass) {
        return getInstancer((Type) tClass);
    }

    protected <T> InstanceSupport<T> getInstancer(Type type) {
        InstanceSupport<?> instanceSupport;
        if ((instanceSupport = instancerMap.get(getKey(type))) == null
                && type instanceof ParameterizedType) {
            instanceSupport = instancerMap.get(getKey((ParameterizedType) type));
        }
        return (InstanceSupport<T>) instanceSupport;
    }

    protected <T> InstanceSupport<List<T>> getListInstancer(Type tType) {
        InstanceSupport<?> instanceSupport = instancerMap.get(getListKey(tType));
        if (instanceSupport == null) {
            instanceSupport = instancerMap.get(getListKey(null));
        }
        return (InstanceSupport<List<T>>) instanceSupport;
    }
}
