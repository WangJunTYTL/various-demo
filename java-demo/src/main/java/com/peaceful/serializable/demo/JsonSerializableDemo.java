package com.peaceful.serializable.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 * Created by JunWang on 2016/11/28.
 */
public class JsonSerializableDemo {

    public static void main(String[] args) {
        // 在Fastjson中 先判断该元素类型，如下面list，它会采用ListSerializer工具类进行序列化，在其内部实现是，判断元素类型，写入[1,2,3]的文本
        System.out.println(JSON.toJSONString(Lists.newArrayList(1,2,3)));
        System.out.println(JSON.toJSONString(new User("WJ",28)));
        System.out.println(JSON.toJSONString(new N<Integer>("WJ",28)));

        JSON.parseObject(JSON.toJSONString(new N<Integer>("WJ",28)),N.class);
    }

    public static class N<T> {
        public String name;
        public T a;



        public N(String name,T a){
            this.name = name;
            this.a = a;
        }

    }
}
