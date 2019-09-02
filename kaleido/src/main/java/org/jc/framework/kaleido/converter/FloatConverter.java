package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/3/12
 */
public class FloatConverter extends AbstractConverter<Float, Float> implements Converter<Float, Float> {

    public FloatConverter(Float defaultValue) {
        super(defaultValue);
    }

    /**
     * 进行转换
     *
     * @param source
     * @return
     */
    @Override
    public Float convert(Float source) {
        if (source == null) {
            return getDefaultValue();
        }
        return source;
    }
}
