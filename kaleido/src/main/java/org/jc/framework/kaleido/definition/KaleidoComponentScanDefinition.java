package org.jc.framework.kaleido.definition;

import org.jc.framework.kaleido.annotation.KaleidoComponentScan;

import java.util.Arrays;

/**
 * @author jc
 * @date 2019/8/21 0:26
 */
public class KaleidoComponentScanDefinition {

    /**
     * 扫描包名列表
     *
     * @return
     */
    private final String[] basePackages;

    /**
     * 匹配模式
     *
     * @return
     */
    private final String resourcePattern;

    public KaleidoComponentScanDefinition(String[] basePackages, String resourcePattern) {
        this.basePackages = basePackages;
        this.resourcePattern = resourcePattern;
    }

    public KaleidoComponentScanDefinition(KaleidoComponentScan kaleidoComponentScan) {
        this.basePackages = kaleidoComponentScan.basePackages();
        this.resourcePattern = kaleidoComponentScan.resourcePattern();
    }

    public String[] getBasePackages() {
        return basePackages;
    }

    public String getResourcePattern() {
        return resourcePattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KaleidoComponentScanDefinition that = (KaleidoComponentScanDefinition) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(basePackages, that.basePackages)) {
            return false;
        }
        return resourcePattern != null ? resourcePattern.equals(that.resourcePattern) : that.resourcePattern == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(basePackages);
        result = 31 * result + (resourcePattern != null ? resourcePattern.hashCode() : 0);
        return result;
    }
}
