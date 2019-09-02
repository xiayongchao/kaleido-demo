package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.Kaleidoscope;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiayc
 * @date 2018/7/26
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T> {
    @Autowired
    protected Kaleidoscope kaleidoscope;
    private final Class<T> tClass;

    public AbstractConverter() {
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
    }

    @Override
    public T convert(S source, Object... objects) {
        if (source == null) {
            return null;
        }
        T target;
        if ((target = newTarget(source)) == null) {
            return null;
        }
        copyProperties(target, source, objects);
        return target;
    }

    @Override
    public void copyProperties(T target, S source, Object... objects) {

    }

    /**
     * 实例化目标对象
     *
     * @param source
     * @return
     */
    protected T newTarget(S source, Object... objects) {
        if (source == null) {
            return null;
        }
        if (tClass == null) {
            throw new RuntimeException("目标类型是泛型类型,需要重写newTarget方法主动创建");
        }
        try {
            return tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("转换对象失败", e);
        }
    }
}
