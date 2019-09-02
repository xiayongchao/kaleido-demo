package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/3/12
 */
public class ByteConverter extends AbstractConverter<Byte, Byte> {
    @Override
    public Byte convert(Byte source, Object... objects) {
        if (source == null) {
            return getDefaultValue();
        }
        return source;
    }
}
