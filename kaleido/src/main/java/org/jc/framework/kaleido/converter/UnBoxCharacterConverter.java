package org.jc.framework.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.instancer.InstanceSupport;

/**
 * @author xiayc
 * @date 2019/3/12
 */
@Converter
@TypeRecognition(sourceClass = Character.class, targetClass = char.class)
public class UnBoxCharacterConverter extends AbstractConverter<Character, Character> {

    @Override
    public Character convert(Character source) {
        if (source == null) {
            InstanceSupport<Character> instanceSupport = kaleidoscope.getInstancer(char.class);
            if (instanceSupport == null) {
                throw new KaleidoException("没有找到[char]基本数据类型的Instancer");
            }
            return instanceSupport.getDefault();
        }
        return source;

    }

    @Override
    public void copyProperties(Character source, Character target) {
        throw new KaleidoException("[char]基本数据类型不支持copyProperties操作");
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Character newTarget(Character source) {
        throw new KaleidoException("[char]基本数据类型不支持newTarget操作");
    }
}
