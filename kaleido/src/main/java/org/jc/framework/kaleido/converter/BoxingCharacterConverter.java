package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@Converter
@TypeRecognition(sourceClass = char.class, targetClass = Character.class)
public class BoxingCharacterConverter extends AbstractConverter<Character, Character> {

    @Override
    public Character convert(Character source) {
        return source;
    }

    @Override
    public void copyProperties(Character source, Character target) {
        throw new KaleidoException("[Character]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Character newTarget() {
        throw new KaleidoException("[Character]基本数据类型不支持newTarget操作");
    }
}
