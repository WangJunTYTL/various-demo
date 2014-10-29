/*
 * Copyright (c) 2014.
 * Author WangJun
 * Email  wangjuntytl@163.com
 */

package com.peaceful.ueditor;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Date 14-8-30.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class Response implements Serializable {
    public  int code;
    public String result;
    public Response(int code, String result){
        this.code=code;
        this.result=result;
    }

    public static void jsonResponse(HttpServletResponse response_,int code,String result){
        HttpServletResponse response = response_;
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(new Response(code,result)));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
