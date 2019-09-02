package org.jc.framework.kaleido.starter;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.annotation.EnableKaleidoConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

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

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean(name = "org.jc.framework.kaleido.Kaleidoscope")
    public Kaleidoscope kaleidoscope() {
        return new Kaleidoscope();
    }
}
