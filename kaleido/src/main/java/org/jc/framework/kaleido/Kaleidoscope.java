package org.jc.framework.kaleido;


import org.jc.framework.kaleido.converter.Converters;
import org.jc.framework.kaleido.core.ConvertSupporter;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public class Kaleidoscope extends ConvertSupporter {
    public <S, T> T convert(Class<S> sClass, Class<T> tClass, S source) {
        return convert((Type) sClass, (Type) tClass, source);
    }

    public <S, T> List<T> convertList(Class<S> sClass, Class<T> tClass, List<S> sourceList) {
        return convertList((Type) sClass, (Type) tClass, sourceList);
    }

    private <S, T> List<T> convertList(Type sType, Type tType, List<S> sourceList) {
        return convert(sType, tType, sourceList);
    }

    private <S, T> List<T> convert(Type sType, Type tType, List<S> sourceList) {
        Converters<List<S>, List<T>> converter = getListConverter(sType, tType);
        if (converter != null) {
            return converter.convert(sourceList);
        }
        Instancers<List<T>> instancers = getListInstancer(tType);
        if (instancers == null) {
            throw new KaleidoException("实例化[%s<%s>]失败，请提供Instancer", List.class.getName(), tType.getTypeName());
        }
        if (sourceList == null) {
            return instancers.getDefault();
        }
        List<T> targetList = instancers.newInstance();
        for (S source : sourceList) {
            targetList.add(convert(sType, tType, source));
        }
        return targetList;
    }

    private <S, T> T convert(Type sType, Type tType, S source) {
        Converters<S, T> converters = getConverter(sType, tType);
        if (converters != null) {
            return converters.convert(source);
        }
        Instancers<T> instancers = getInstancer(tType);
        Class<T> tClass = (Class<T>) tType;
        Class<S> sClass = (Class<S>) sType;
        T target;
        if (instancers != null) {
            if (source == null) {
                return instancers.getDefault();
            }
            target = instancers.newInstance();
        } else {
            try {
                target = tClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new KaleidoException(e, "实例化[%s]失败，请提供Instancer", tClass.getName());
            }
        }
        Field sField;
        Type sFieldType, tFieldType;
        ParameterizedType sParameterizedType, tParameterizedType;
        for (Field tField : tClass.getDeclaredFields()) {
            try {
                sField = sClass.getDeclaredField(tField.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            tField.setAccessible(true);
            sField.setAccessible(true);
            try {
                if (isEqual(sFieldType = sField.getGenericType(), tFieldType = tField.getGenericType())) {
                    tField.set(target, sField.get(source));
                } else {
                    if (sFieldType instanceof ParameterizedType && tFieldType instanceof ParameterizedType
                            && (sParameterizedType = (ParameterizedType) sFieldType).getRawType().equals(List.class)
                            && (tParameterizedType = (ParameterizedType) tFieldType).getRawType().equals(List.class)) {
                        tField.set(target, convertList(sParameterizedType.getActualTypeArguments()[0],
                                tParameterizedType.getActualTypeArguments()[0], (List<? extends Object>) sField.get(source)));
                    } else {
                        tField.set(target, convert(sFieldType, tFieldType, sField.get(source)));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new KaleidoException(e, "没有找到[%s]的[%s]的转换器", sField.getType().getName(), tField.getType().getName());
            }
        }
        return target;
    }
}
