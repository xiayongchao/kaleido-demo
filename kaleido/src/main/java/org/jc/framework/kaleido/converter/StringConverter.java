package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/3/14
 */
public class StringConverter extends AbstractConverter<String, String> implements Converter<String, String> {
    public StringConverter(String defaultValue) {
        super(defaultValue);
    }

    /**
     * 进行转换
     *
     * @param source
     * @return
     */
    @Override
    public String convert(String source) {
        if (source == null) {
            return getDefaultValue();
        }
        return source;
    }
}
