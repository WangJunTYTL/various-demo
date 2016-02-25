package com.peaceful.demo.spring.webmvc;

import com.peaceful.common.util.Http;
import com.peaceful.demo.spring.domain.User;
import com.peaceful.demo.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Date 14/11/1.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping({"user", ""})
    @ResponseBody
    public void test() {
        User user = userService.getUserById(165);
        if (user != null)
            Http.responseJSON(1, user.getName());
        else
            Http.responseJSON(0, "not found user");
    }

    @RequestMapping("jsp")
    public String testJsp() {
        return "test";
    }

    @RequestMapping("ftl")
    public String testFtl() {
        return "test.ftl";
    }

}
