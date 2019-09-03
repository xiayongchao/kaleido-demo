package org.jc.test.kaleido;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.test.kaleido.entity.*;
import org.jc.test.kaleido.util.Gsons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KaleidoTestApplicationTests {
    @Autowired
    private Kaleidoscope kaleidoscope;

    @Test
    public void contextLoads_0() {
        User user = new User(null, null, null);
        user.setUserExt(new UserExt(12L, "fdasfas", 432.342f));
        user.setCommentList(Arrays.asList(new Comment(1L, "fda"), new Comment(2L, "hrwt")));
        UserInfo userInfo = kaleidoscope.convert(User.class, UserInfo.class, user);
        System.out.println(Gsons.getJson(userInfo));
    }

    @Test
    public void contextLoads_1() {
        List<Comment> commentList = Arrays.asList(new Comment(1L, "fda"), new Comment(2L, "hrwt"));
        List<CommentInfo> commentInfoList = kaleidoscope.convertList(Comment.class, CommentInfo.class, commentList);
        System.out.println(Gsons.getJson(commentInfoList));
    }

}
