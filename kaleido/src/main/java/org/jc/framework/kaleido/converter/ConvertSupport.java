package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public interface ConvertSupport<S, T> {
    /**
     * 转换对象S到T
     *
     * @param source
     * @return
     */
    T convert(S source);

    /**
     * 拷贝S的属性值到T
     *
     * @param source
     * @param target
     */
    void copyProperties(S source, T target);
}
