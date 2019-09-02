package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.exception.KaleidoException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public abstract class AbstractInstancer<T> implements Instancer<T> {
    /**
     * 默认值
     */
    private final T defaultValue;
    private final Class<T> tClass;

    public AbstractInstancer(T defaultValue) {
        //子类创建 会创建父类 子类调用时 此处的this是子类
        Class<?> c = this.getClass();
        //获得带有泛型的父类
        Type t = c.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            //取得所有泛型
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            if (p[p.length - 1] instanceof ParameterizedType) {
                this.tClass = null;
            } else {
                this.tClass = (Class<T>) p[p.length - 1];
            }
        } else {
            throw new RuntimeException("请提供要转换的目标对象类型");
        }
        this.defaultValue = null;
    }

    @Override
    public T newInstance() {
        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new KaleidoException(e, "实例化[%s]失败", tClass.getName());
        }
    }

    @Override
    public T getDefault() {
        return defaultValue;
    }
}
