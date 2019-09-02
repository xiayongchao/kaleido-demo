package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/3/12
 */
public class CharacterConverter extends AbstractConverter<Character, Character> {

    /**
     * 进行转换
     *
     * @param source
     * @return
     */
    @Override
    public Character convert(Character source) {
        if (source == null) {
            return getDefaultValue();
        }
        return source;
    }
}
