package org.jc.framework.kaleido.converter;

import org.springframework.beans.BeanUtils;

/**
 * @author xiayc
 * @date 2018/7/26
 */
public abstract class AbstractDoubleConverter<S, E, T> extends AbstractConverter<S, T> implements DoubleConverter<S, E, T> {
    @Override
    public T convert(S source, E extend) {
        if (source == null) {
            return null;
        }
        T target;
        if ((target = newTarget(source)) == null) {
            throw new RuntimeException("newTarget方法返回值不能为null");
        }
        copyProperties(source, extend, target);
        return target;
    }

    /**
     * 从source拷贝属性到target
     *
     * @param source
     * @param extend
     * @param target
     */
    public void copyProperties(S source, E extend, T target) {
        BeanUtils.copyProperties(source, target);
    }
}
