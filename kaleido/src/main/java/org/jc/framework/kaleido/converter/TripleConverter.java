package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/5/10
 */
public interface TripleConverter<S, E, A, T> extends DoubleConverter<S, E, T> {
    /**
     * 3转1
     *
     * @param source
     * @param extend
     * @param attach
     * @return
     */
    T convert(S source, E extend, A attach);

    void copyProperties(S source, E extend, A attach, T target);
}
