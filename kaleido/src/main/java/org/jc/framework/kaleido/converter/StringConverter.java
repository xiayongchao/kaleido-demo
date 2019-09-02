package org.jc.framework.kaleido.converter;

/**
 * @author xiayc
 * @date 2019/3/14
 */
public class StringConverter extends AbstractConverter<String, String> {
    @Override
    public String convert(String source) {
        return super.convert(source);
    }

    @Override
    public void copyProperties(String source, String target) {
        super.copyProperties(source, target);
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected String newTarget() {
        return super.newTarget();
    }
}
