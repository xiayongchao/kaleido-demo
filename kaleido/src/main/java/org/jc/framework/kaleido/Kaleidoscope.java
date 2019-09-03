package org.jc.framework.kaleido;


import org.jc.framework.kaleido.converter.AbstractConverter;
import org.jc.framework.kaleido.converter.Converter;
import org.jc.framework.kaleido.instancer.AbstractInstancer;
import org.jc.framework.kaleido.instancer.Instancer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public class Kaleidoscope {
    private final Map<String, Converter<?, ?>> converterMap = new ConcurrentHashMap<>();
    private final Map<String, Instancer<?>> instancerMap = new ConcurrentHashMap<>();

    public void registerConverter(AbstractConverter converter) {
        Type[] actualTypeArguments = ((ParameterizedType) converter.getClass().getGenericSuperclass()).getActualTypeArguments();
        converterMap.put(getKey(actualTypeArguments), converter);
    }

    public <S, T> Converter<S, T> getConverter(Class<S> sClass, Class<T> tClass) {
        return (Converter<S, T>) converterMap.get(getKey(sClass, tClass));
    }

    public void registerInstancer(AbstractInstancer instancer) {
        Type[] actualTypeArguments = ((ParameterizedType) instancer.getClass().getGenericSuperclass()).getActualTypeArguments();
        instancerMap.put(getKey(actualTypeArguments), instancer);
    }

    public <T> Instancer<T> getInstancer(Class<T> tClass) {
        return (Instancer<T>) instancerMap.get(getKey(tClass));
    }

    public <S, T> T convert(S source){

    }

    private String getKey(Type... types) {
        StringJoiner stringJoiner = new StringJoiner("_");
        for (Type type : types) {
            stringJoiner.add(type.getTypeName());
        }
        return stringJoiner.toString();
    }
}
