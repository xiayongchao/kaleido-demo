package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/14
 */
public class StringConverter extends AbstractConverter<String, String> {
    @Override
    public String convert(String source) {
        return source == null ? null : source;

    }

    @Override
    public void copyProperties(String source, String target) {
        throw new KaleidoException("[String]数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected String newTarget() {
        throw new KaleidoException("[String]数据类型不支持newTarget操作");
    }
}
