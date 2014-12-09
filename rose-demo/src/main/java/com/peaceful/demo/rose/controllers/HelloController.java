package com.peaceful.demo.rose.controllers;

/**
 * Created by wangjun on 14/12/8.
 *
 * rose 默认扫描的controller必须在包controllers下
 */

import com.peaceful.common.util.Util;
import com.peaceful.demo.rose.domain.User;
import com.peaceful.demo.rose.service.UserService;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.var.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/hello/")
public class HelloController {

    @Autowired
    private UserService userService;


    @Get("world")
    public String world() {
        return "@hello world，我爱你"; // 支持中文编码
    }

    @Get("{topicId:[0-9]+}") // 这里必须写正则表达式，好麻烦
    public String showTopic(@Param("topicId") int topicId) {
        return "@" + topicId;
    }


    @Get("aa/{topicId:[0-9]+}") // 这里必须写正则表达式，好麻烦
    public String aa(@Param("topicId") int topicId, Model model) {
        model.add("id", topicId);
        return "test";

    }


    /**
     * 所有的基础java类型,都可以直接使用,rose进行自动转换,比如在action中的类型为long id,则id可以转为数字,不再需要从string转为long。 l array/map/bean同样可用,它们的接收参数规则为:
     * ¡ ?id=1,2,3,4 或者 ?id=1&id=2&id=3 对应 @Param("id") int[] idArray
     * ¡ ?map:1=paoding&map:2=rose 对应 @Param("map") Map map
     *
     * @param user
     * @param model
     * @return
     */
    @Get("bb") // 自动反射成想要的对象
    public String bb(User user, Model model) {
        model.add("user", user);
        return "testUser";

    }

    @Get("cc")
    public String cc(Model model) {
        User user = userService.getUser();
        Util.report(user.id);
        return "@".concat(user.id+"aa");

    }


}

