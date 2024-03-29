package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.instancer.InstanceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiayc
 * @date 2018/7/26
 */
public abstract class AbstractConverter<S, T> implements ConvertSupport<S, T> {
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
    public T convert(S source) {
        if (source == null) {
            return null;
        }
        T target;
        if ((target = newTarget(source)) == null) {
            throw new RuntimeException("newTarget方法返回值不能为null");
        }
        copyProperties(source, target);
        return target;
    }

    @Override
    public void copyProperties(S source, T target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    protected T newTarget(S source) {
        if (tClass == null) {
            throw new RuntimeException("目标类型是泛型类型,需要重写newTarget方法主动创建实例");
        }
        InstanceSupport<T> instancer = kaleidoscope.getInstancer(tClass);
        if (instancer != null) {
            return instancer.newInstance();
        }
        try {
            return tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("转换对象失败", e);
        }
    }
}
