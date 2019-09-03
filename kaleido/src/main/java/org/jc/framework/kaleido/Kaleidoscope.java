package org.jc.framework.kaleido;


import org.jc.framework.kaleido.annotation.Null;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.converter.Converters;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.Instancers;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    private final Map<String, Converters<?, ?>> converterMap = new ConcurrentHashMap<>();
    private final Map<String, Instancers<?>> instancerMap = new ConcurrentHashMap<>();

    public void registerConverter(Converters converters) {
        Class<? extends Converters> convertersClass;
        Type[] actualTypeArguments = ((ParameterizedType) (convertersClass = converters.getClass()).getGenericSuperclass()).getActualTypeArguments();
        converterMap.put(getKey(actualTypeArguments[0], actualTypeArguments[1], AnnotationUtils.findAnnotation(convertersClass, TypeRecognition.class)), converters);
    }

    public <S, T> Converters<S, T> getConverter(Class<S> sClass, Class<T> tClass) {
        return (Converters<S, T>) converterMap.get(getKey(sClass, tClass));
    }

    public <S, T> Converters<S, T> getConverter(Type sType, Type tType) {
        Converters<?, ?> converters;
        while ((converters = converterMap.get(getKey(sType, tType))) == null
                && (sType = (Class) ((Class) sType).getGenericSuperclass()) != null) {
            converters = converterMap.get(getKey(sType, tType));
        }
        return (Converters<S, T>) converters;
    }

    public void registerInstancer(Instancers instancers) {
        Class<? extends Instancers> instancersClass;
        Type[] actualTypeArguments = ((ParameterizedType) (instancersClass = instancers.getClass()).getGenericSuperclass()).getActualTypeArguments();
        instancerMap.put(getKey(actualTypeArguments[0], AnnotationUtils.findAnnotation(instancersClass, TypeRecognition.class)), instancers);
    }

    public <T> Instancers<T> getInstancer(Class<T> tClass) {
        return (Instancers<T>) instancerMap.get(getKey(tClass));
    }

    public <T> Instancers<T> getInstancer(Type tType) {
        return (Instancers<T>) instancerMap.get(getKey(tType));
    }

    public <S, T> T convert(Class<S> sClass, Class<T> tClass, S source) {
        Converters<S, T> converters = getConverter(sClass, tClass);
        if (converters != null) {
            return converters.convert(source);
        }
        Instancers<T> instancers = getInstancer(tClass);
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
        for (Field tField : tClass.getDeclaredFields()) {
            try {
                sField = sClass.getDeclaredField(tField.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            tField.setAccessible(true);
            sField.setAccessible(true);
            try {
                if (sField.getType().equals(tField.getType())) {
                    tField.set(target, sField.get(source));
                } else {
                    tField.set(target, convert(sField.getType(), tField.getType(), sField.get(source)));
                }
            } catch (IllegalAccessException e) {
                throw new KaleidoException(e, "没有找到[%s]的[%s]的转换器", sField.getType().getName(), tField.getType().getName());
            }
        }
        return target;
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
        for (Field tField : tClass.getDeclaredFields()) {
            try {
                sField = sClass.getDeclaredField(tField.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            tField.setAccessible(true);
            sField.setAccessible(true);
            try {
                if (sField.getType().equals(tField.getType())) {
                    tField.set(target, sField.get(source));
                } else {
                    tField.set(target, convert(sField.getType(), tField.getType(), sField.get(source)));
                }
            } catch (IllegalAccessException e) {
                throw new KaleidoException(e, "没有找到[%s]的[%s]的转换器", sField.getType().getName(), tField.getType().getName());
            }
        }
        return target;
    }

    private String getKey(Type sType, Type tType, TypeRecognition typeRecognition) {
        String sourceClassName = typeRecognition != null && !Null.class.equals(typeRecognition.sourceClass()) ? typeRecognition.sourceClass().getName() : null;
        String targetClassName = typeRecognition != null && !Null.class.equals(typeRecognition.targetClass()) ? typeRecognition.targetClass().getName() : null;
        return String.format("%s_%s", sourceClassName != null ? sourceClassName : sType.getTypeName(), targetClassName != null ? targetClassName : tType.getTypeName());
    }

    private String getKey(Type tType, TypeRecognition typeRecognition) {
        String targetClassName = typeRecognition != null && !Null.class.equals(typeRecognition.targetClass()) ? typeRecognition.targetClass().getName() : null;
        return targetClassName != null ? targetClassName : tType.getTypeName();
    }

    private String getKey(Type... types) {
        StringJoiner stringJoiner = new StringJoiner("_");
        for (Type type : types) {
            stringJoiner.add(type.getTypeName());
        }
        return stringJoiner.toString();
    }
}
