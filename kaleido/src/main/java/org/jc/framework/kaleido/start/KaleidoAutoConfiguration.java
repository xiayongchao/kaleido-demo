package org.jc.framework.kaleido.start;

import org.jc.framework.kaleido.annotation.EnableKaleidoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author jc
 * @date 2019/9/2 0:16
 */
@Configuration
@ConditionalOnBean(annotation = EnableKaleidoConfiguration.class)
@EnableConfigurationProperties(KaleidoProperties.class)
public class KaleidoAutoConfiguration {
    /**
     * 使用配置
     */
    @Resource
    private KaleidoProperties properties;

    public KaleidoAutoConfiguration() {
    }
}
