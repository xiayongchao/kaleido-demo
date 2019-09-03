package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.KaleidoConverter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@KaleidoConverter
@TypeRecognition(sourceClass = Number.class, targetClass = Long.class)
public class BoxingLongConverter extends AbstractConverter<Number, Long> {
    @Override
    public Long convert(Number source) {
        return source == null ? null : source.longValue();
    }

    @Override
    public void copyProperties(Number source, Long target) {
        throw new KaleidoException("[Long]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Long newTarget() {
        throw new KaleidoException("[Long]基本数据类型不支持newTarget操作");
    }
}
