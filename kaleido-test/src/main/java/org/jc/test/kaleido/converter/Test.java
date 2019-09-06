package org.jc.test.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.converter.AbstractDoubleConverter;

@Converter
public class Test extends AbstractDoubleConverter<Integer, Long, String> {
    @Override
    public String convert(Integer source, Long extend) {
        return String.valueOf(source + extend);
    }
}
