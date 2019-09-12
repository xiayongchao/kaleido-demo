package org.jc.framework.kaleido.core;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.converter.ConvertSupport;
import org.jc.framework.kaleido.converter.DoubleConverter;
import org.jc.framework.kaleido.converter.TripleConverter;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.converter.Converter;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

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
    private final Map<String, ConvertSupport<?, ?>> converterMap = new ConcurrentHashMap<>();

    public void registerConverter(ConvertSupport convertSupport) {
        Class<? extends ConvertSupport> convertersClass;
        Type[] actualTypeArguments = ((ParameterizedType) (convertersClass = convertSupport.getClass()).getGenericSuperclass()).getActualTypeArguments();
        converterMap.put(getKey(actualTypeArguments[0], actualTypeArguments[1], AnnotationUtils.findAnnotation(convertersClass, TypeRecognition.class)), convertSupport);
    }

    public <S, T> ConvertSupport<S, T> getConverter(Class<S> sClass, Class<T> tClass) {
        return getConverter((Type) sClass, (Type) tClass);
    }

    @SuppressWarnings("unchecked")
    protected <S, T> ConvertSupport<List<S>, List<T>> getListConverter(Type sType, Type tType) {
        return (ConvertSupport<List<S>, List<T>>) converterMap.get(getListKey(sType, tType));
    }

    private boolean isNumber(Type type) {
        if (type.getTypeName().equals(float.class.getName())
                || type.getTypeName().equals(double.class.getName())
                || type.getTypeName().equals(byte.class.getName())
                || type.getTypeName().equals(short.class.getName())
                || type.getTypeName().equals(int.class.getName())
                || type.getTypeName().equals(long.class.getName())) {
            return true;
        }
        return false;
    }

    private Type getSuperclass(Type type) {
        if (type instanceof ParameterizedTypeImpl) {
            return null;
        }
        return ((Class) type).getGenericSuperclass();
    }

    @SuppressWarnings("unchecked")
    protected <S, T> ConvertSupport<S, T> getConverter(Type sType, Type tType) {
        if (isNumber(sType)) {
            sType = Number.class;
        }
        ConvertSupport<?, ?> convertSupport;
        while ((convertSupport = converterMap.get(getKey(sType, tType))) == null
                && (sType = getSuperclass(sType)) != null) {
        }
        return (ConvertSupport<S, T>) convertSupport;
    }

    @SuppressWarnings("unchecked")
    public <S, T> Converter<S, T> get(Type sType, Type tType) {
        String key = getKey(sType, tType);
        Converter<?, ?> converter = (Converter<?, ?>) converterMap.get(key);
        if (converter == null) {
            throw new KaleidoException("没有找到[%s]到[%s]的转换器，请进行注册", sType.getTypeName(), tType.getTypeName());
        }
        return (Converter<S, T>) converter;
    }

    @SuppressWarnings("unchecked")
    public <S, E, T> DoubleConverter<S, E, T> get(Type sType, Type eType, Type tType) {
        String key = getKey(sType, eType, tType);
        DoubleConverter<?, ?, ?> converter = (DoubleConverter<?, ?, ?>) converterMap.get(key);
        if (converter == null) {
            throw new KaleidoException("没有找到[%s,%s]到[%s]的转换器，请进行注册", sType.getTypeName(), eType.getTypeName(), tType.getTypeName());
        }
        return (DoubleConverter<S, E, T>) converter;
    }

    @SuppressWarnings("unchecked")
    public <S, E, A, T> TripleConverter<S, E, A, T> get(Type sType, Type eType, Type aType, Type tType) {
        String key = getKey(sType, eType, aType, tType);
        TripleConverter<?, ?, ?, ?> converter = (TripleConverter<?, ?, ?, ?>) converterMap.get(key);
        if (converter == null) {
            throw new KaleidoException("没有找到[%s,%s,%s]到[%s]的转换器，请进行注册", sType.getTypeName(), eType.getTypeName(), aType.getTypeName(), tType.getTypeName());
        }
        return (TripleConverter<S, E, A, T>) converter;
    }
}
