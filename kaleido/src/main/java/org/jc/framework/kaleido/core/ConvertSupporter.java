package org.jc.framework.kaleido.core;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.converter.Converters;
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
public abstract class ConvertSupporter extends InstanceSupporter {
    private final Map<String, Converters<?, ?>> converterMap = new ConcurrentHashMap<>();

    public void registerConverter(Converters converters) {
        Class<? extends Converters> convertersClass;
        Type[] actualTypeArguments = ((ParameterizedType) (convertersClass = converters.getClass()).getGenericSuperclass()).getActualTypeArguments();
        converterMap.put(getKey(actualTypeArguments[0], actualTypeArguments[1], AnnotationUtils.findAnnotation(convertersClass, TypeRecognition.class)), converters);
    }

    public <S, T> Converters<S, T> getConverter(Class<S> sClass, Class<T> tClass) {
        return getConverter((Type) sClass, (Type) tClass);
    }

    protected <S, T> Converters<List<S>, List<T>> getListConverter(Type sType, Type tType) {
        return (Converters<List<S>, List<T>>) converterMap.get(getListKey(sType, tType));
    }

    protected <S, T> Converters<S, T> getConverter(Type sType, Type tType) {
        if (sType.getTypeName().equals(float.class.getName())
                || sType.getTypeName().equals(double.class.getName())
                || sType.getTypeName().equals(byte.class.getName())
                || sType.getTypeName().equals(short.class.getName())
                || sType.getTypeName().equals(int.class.getName())
                || sType.getTypeName().equals(long.class.getName())) {
            sType = Number.class;
        }
        Converters<?, ?> converters;
        while ((converters = converterMap.get(getKey(sType, tType))) == null
                && (sType = (Class) ((Class) sType).getGenericSuperclass()) != null) {
            converters = converterMap.get(getKey(sType, tType));
        }
        return (Converters<S, T>) converters;
    }
}
