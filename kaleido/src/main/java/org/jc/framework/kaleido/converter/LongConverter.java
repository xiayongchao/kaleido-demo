package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/3/12
 */
public class LongConverter extends AbstractConverter<Long, Long> implements Converter<Long, Long> {
    public LongConverter(Long defaultValue) {
        super(defaultValue);
    }

    /**
     * 进行转换
     *
     * @param source
     * @return
     */
    @Override
    public Long convert(Long source) {
        if (source == null) {
            return getDefaultValue();
        }
        return source;
    }
}
