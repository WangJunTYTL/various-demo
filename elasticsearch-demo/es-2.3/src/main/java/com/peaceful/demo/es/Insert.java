package com.peaceful.demo.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.helpers.Util;

import java.io.IOException;
import java.util.Date;

/**
 * Created by wangjun on 16/2/5.
 */
public class Insert {

    public static void main(String[] args) throws IOException {
        Client client = ConnDemo.getConn();
        // 构造json对象，这是es提供的json操作api，也可以使用外部
        String users = XContentFactory.jsonBuilder().startObject().field("name", "wj").field("age", 28).endObject().string();
        Util.report(users);
        // 采用future模式请求
        client.prepareIndex("test", "users").setSource(users).execute().actionGet();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.age = (int) (Math.random() * 66);
            user.createTime = new Date();
            user.updateTime = new Date();
            // 空格会产生分词操作
            user.name = getName();
            client.prepareIndex("test", "users").setSource(JSON.toJSONString(user)).execute().actionGet();
        }
    }

    private static String getName() {
        char[] up = new char[26];
        for (int n = 65; n < 65 + 26; n++) {
            up[n - 65] = (char) n;
        }
        char[] low = new char[26];
        for (int n = 97; n < 97 + 26; n++) {
            low[n - 97] = (char) n;
        }

        char[] name = new char[9];
        for (int i = 0; i < 9; i++) {
            int v = (int) (Math.random() * 26);
            if (i == 0)
                name[i] = up[v];
            else if (i == 4)
                name[i] = " ".toCharArray()[0];
            else
                name[i] = low[v];
        }
        return new String(name);

    }
}
