package org.jc.test.kaleido;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.test.kaleido.entity.User;
import org.jc.test.kaleido.entity.UserExt;
import org.jc.test.kaleido.entity.UserInfo;
import org.jc.test.kaleido.util.Gsons;
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
        User user = new User(null, null, null);
        user.setUserExt(new UserExt(12L, "fdasfas", 432.342f));
        UserInfo userInfo = kaleidoscope.convert(User.class, UserInfo.class, user);
        System.out.println(Gsons.getJson(userInfo));
    }

}
