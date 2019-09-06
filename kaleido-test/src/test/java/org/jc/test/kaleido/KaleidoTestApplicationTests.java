package org.jc.test.kaleido;

import org.jc.framework.kaleido.Kaleidoscope;
import org.jc.framework.kaleido.core.TypeToken;
import org.jc.test.kaleido.entity.Comment;
import org.jc.test.kaleido.entity.CommentInfo;
import org.jc.test.kaleido.entity.Page;
import org.jc.test.kaleido.entity.PageInfo;
import org.jc.test.kaleido.entity.User;
import org.jc.test.kaleido.entity.UserExt;
import org.jc.test.kaleido.entity.UserInfo;
import org.jc.test.kaleido.util.Gsons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        kaleidoscope.convertList(List.class, List.class, Arrays.asList(commentList));
        kaleidoscope.convert(List.class, List.class, commentList);
        System.out.println(Gsons.getJson(commentInfoList));
    }

    @Test
    public void contextLoads_2() {
        Map<String, Integer> map = new HashMap<>();
        map.put("x", 1);
        map.put("y", 2);
        map.put("c", 3);
        Map<String, Long> o = kaleidoscope.convertObject(new TypeToken<Map<String, Integer>>() {
        }, new TypeToken<Map<String, Long>>() {
        }, map);
        System.out.println(Gsons.getJson(o));
    }

    @Test
    public void contextLoads_3() {
        Page<Comment> commentPage = new Page<>();
        commentPage.setT(new Comment(3L, "大厦"));
        commentPage.setCount(43);
        commentPage.setList(Arrays.asList(new Comment(1L, "fda"), new Comment(2L, "hrwt")));
        PageInfo<CommentInfo> pageInfo = kaleidoscope.convert(Page.class, PageInfo.class, commentPage);
        System.out.println(Gsons.getJson(pageInfo));
    }

    @Test
    public void contextLoads_4() {
        String s = kaleidoscope.convertObject(Integer.class, Long.class, String.class, 1, 2L);
        System.out.println(s);
    }
}
