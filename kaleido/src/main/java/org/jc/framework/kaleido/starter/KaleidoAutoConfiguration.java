package org.jc.framework.kaleido.starter;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.annotation.EnableKaleido;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author jc
 * @date 2019/9/2 0:16
 */
@Configuration
@ConditionalOnBean(annotation = EnableKaleido.class)
public class KaleidoAutoConfiguration {
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean(name = "org.jc.framework.kaleido.Kaleidoscope")
    public Kaleidoscope kaleidoscope() {
        return new Kaleidoscope();
    }

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean(name = "org.jc.framework.kaleido.KaleidoBeanPostProcessor")
    public KaleidoBeanPostProcessor kaleidoBeanPostProcessor(Kaleidoscope kaleidoscope) {
        return new KaleidoBeanPostProcessor(kaleidoscope);
    }
}
