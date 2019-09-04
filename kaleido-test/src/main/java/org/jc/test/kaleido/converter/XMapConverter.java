package org.jc.test.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.converter.AbstractConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jc
 * @date 2019/9/4 23:40
 */
//@Converter
public class XMapConverter extends AbstractConverter<Map<String, Integer>, Map<String, Long>> {
    @Override
    public void copyProperties(Map<String, Integer> source, Map<String, Long> target) {
        for (Map.Entry<String, Integer> entry : source.entrySet()) {
            target.put(entry.getKey(), Long.valueOf(entry.getValue()));
        }
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected Map<String, Long> newTarget() {
        return new HashMap<>();
    }
}
