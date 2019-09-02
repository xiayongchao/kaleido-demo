package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public interface Converter<S, T> {
    T convert(S source, Object... objects);

    void copyProperties(T target, S source, Object... objects);
}
