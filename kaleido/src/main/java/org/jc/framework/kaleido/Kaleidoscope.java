package org.jc.framework.kaleido;


import org.jc.framework.kaleido.converter.ConvertSupport;
import org.jc.framework.kaleido.core.ConvertSupporter;
import org.jc.framework.kaleido.core.TypeToken;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.InstanceSupport;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public class Kaleidoscope extends ConvertSupporter {
    public <S, T> T convert(Class<S> sClass, Class<T> tClass, S source) {
        return convert((Type) sClass, (Type) tClass, source);
    }

    private <S, T> T convert(Type sType, Type tType, S source) {
        ConvertSupport<S, T> convertSupport = getConverter(sType, tType);
        if (convertSupport != null) {
            return convertSupport.convert(source);
        }
        if (sType.getTypeName().equals(List.class.getName())
                && tType.getTypeName().equals(List.class.getName())) {
            throw new KaleidoException("不支持List转换，请使用convertList方法");
        }
        InstanceSupport<T> instanceSupport = getInstancer(tType);
        Class<T> tClass = (Class<T>) getTypeClass(tType);
        Class<S> sClass = (Class<S>) getTypeClass(sType);
        T target;
        if (instanceSupport != null) {
            if (source == null) {
                return instanceSupport.getDefault();
            }
            target = instanceSupport.newInstance();
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
            if (tField.getGenericType() instanceof TypeVariable) {
                throw new KaleidoException("转换字段[%s]失败，不支持的类型[%s]", tField.getName(), TypeVariable.class.getName());
            }
            if (tField.getGenericType() instanceof TypeVariable
                    || sField.getGenericType() instanceof TypeVariable) {
                throw new KaleidoException("转换字段[%s]失败，不支持的类型[%s]", tField.getName(), TypeVariable.class.getName());
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

    public <S, T> List<T> convertList(Class<S> sClass, Class<T> tClass, List<S> sourceList) {
        return convertList((Type) sClass, (Type) tClass, sourceList);
    }

    private <S, T> List<T> convertList(Type sType, Type tType, List<S> sourceList) {
        if (sType.getTypeName().equals(List.class.getName())
                || tType.getTypeName().equals(List.class.getName())) {
            throw new KaleidoException("不支持List<List>转换，请使用convertObject方法");
        }
        ConvertSupport<List<S>, List<T>> converter = getListConverter(sType, tType);
        if (converter != null) {
            return converter.convert(sourceList);
        }
        InstanceSupport<List<T>> instanceSupport = getListInstancer(tType);
        if (instanceSupport == null) {
            throw new KaleidoException("实例化[%s<%s>]失败，请提供Instancer", List.class.getName(), tType.getTypeName());
        }
        if (sourceList == null) {
            return instanceSupport.getDefault();
        }
        List<T> targetList = instanceSupport.newInstance();
        for (S source : sourceList) {
            targetList.add(convert(sType, tType, source));
        }
        return targetList;
    }

    @SuppressWarnings("unchecked")
    public <S, T> T convertObject(Object s, Object t, S source) {
        return (T) get(getType(s), getType(t)).convert(source);
    }

    @SuppressWarnings("unchecked")
    public <S, E, T> T convertObject(Object s, Object e, Object t,
                                     S source, E extend) {
        return (T) get(getType(s), getType(e), getType(t)).convert(source, extend);
    }

    @SuppressWarnings("unchecked")
    public <S, E, A, T> T convertObject(Object s, Object e, Object a,
                                        Object t, S source, E extend, A attach) {
        return (T) get(getType(s), getType(e), getType(a), getType(t)).convert(source, extend, attach);
    }

    private Type getType(Object o) {
        Type type;
        if (o instanceof Class) {
            type = (Class) o;
        } else if (o instanceof TypeToken) {
            type = ((TypeToken) o).getType();
        } else {
            throw new KaleidoException("illegal type of '%s'", o.getClass().getName());
        }
        return type;
    }

    private Class getTypeClass(Type type) {
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        return (Class) type;
    }
}
