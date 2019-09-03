package org.jc.test.kaleido;

import org.jc.framework.kaleido.annotation.EnableKaleido;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yc_xia
 */
@EnableKaleido
@SpringBootApplication
public class KaleidoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaleidoTestApplication.class, args);
    }

}
