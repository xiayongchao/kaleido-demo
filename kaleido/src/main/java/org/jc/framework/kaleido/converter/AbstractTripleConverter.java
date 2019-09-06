package org.jc.framework.kaleido.converter;

import org.springframework.beans.BeanUtils;

/**
 * @author xiayc
 * @date 2018/7/26
 */
public abstract class AbstractTripleConverter<S, E, A, T> extends AbstractDoubleConverter<S, E, T> implements TripleConverter<S, E, A, T> {
    @Override
    public T convert(S source, E extend, A attach) {
        if (source == null) {
            return null;
        }
        T target;
        if ((target = newTarget(source)) == null) {
            throw new RuntimeException("newTarget方法返回值不能为null");
        }
        copyProperties(source, extend, attach, target);
        return target;
    }

    /**
     * 从source拷贝属性到target
     *
     * @param source
     * @param extend
     * @param attach
     * @param target
     */
    public void copyProperties(S source, E extend, A attach, T target) {
        BeanUtils.copyProperties(source, target);
    }
}
