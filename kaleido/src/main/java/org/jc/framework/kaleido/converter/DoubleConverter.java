package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/5/10
 */
public interface DoubleConverter<S, E, T> extends ConvertSupport<S, T> {
    /**
     * 2转1
     *
     * @param source
     * @param extend
     * @return
     */
    T convert(S source, E extend);

    void copyProperties(S source, E extend, T target);
}
