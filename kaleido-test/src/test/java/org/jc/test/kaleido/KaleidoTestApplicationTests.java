package org.jc.test.kaleido;

import org.jc.framework.kaleido.Kaleidoscope;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KaleidoTestApplicationTests {
    @Autowired
    private Kaleidoscope kaleidoscope;

    @Test
    public void contextLoads() {
        kaleidoscope.
    }

}
