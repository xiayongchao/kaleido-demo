package org.jc.framework.kaleido.instancer;

/**
 * @author xiayc
 * @date 2019/9/2
 */
public interface InstanceSupport<T> {
    T newInstance();

    T getDefault();
}
