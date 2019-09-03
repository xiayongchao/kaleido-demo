package org.jc.framework.kaleido.core;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.instancer.Instancers;
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
    private final Map<String, Instancers<?>> instancerMap = new ConcurrentHashMap<>();

    public void registerInstancer(Instancers instancers) {
        Class<? extends Instancers> instancersClass;
        Type[] actualTypeArguments = ((ParameterizedType) (instancersClass = instancers.getClass()).getGenericSuperclass()).getActualTypeArguments();
        instancerMap.put(getKey(actualTypeArguments[0], AnnotationUtils.findAnnotation(instancersClass, TypeRecognition.class)), instancers);
    }

    public <T> Instancers<T> getInstancer(Class<T> tClass) {
        return getInstancer((Type) tClass);
    }

    protected <T> Instancers<T> getInstancer(Type tType) {
        return (Instancers<T>) instancerMap.get(getKey(tType));
    }

    protected <T> Instancers<List<T>> getListInstancer(Type tType) {
        Instancers<?> instancers = instancerMap.get(getListKey(tType));
        if (instancers == null) {
            instancers = instancerMap.get(getListKey(null));
        }
        return (Instancers<List<T>>) instancers;
    }
}
