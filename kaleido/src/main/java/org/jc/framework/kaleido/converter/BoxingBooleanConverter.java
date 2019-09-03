package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@TypeRecognition(sourceClass = boolean.class, targetClass = Boolean.class)
public class BoxingBooleanConverter extends AbstractConverter<Boolean, Boolean> {
    @Override
    public Boolean convert(Boolean source) {
        return source;
    }

    @Override
    public void copyProperties(Boolean source, Boolean target) {
        throw new KaleidoException("[Boolean]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Boolean newTarget() {
        throw new KaleidoException("[Boolean]基本数据类型不支持newTarget操作");
    }
}
