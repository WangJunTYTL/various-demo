package com.peaceful.jexl.demo;

import java.util.List;

/**
 * Created by wangjun on 16/9/4.
 */
public class Alert {

    public boolean term;
    public String msg;
    public String[] phones;
    public String[] mails;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("term->").append(term).append("\n");
        buffer.append("msg->").append(msg).append("\n");
        buffer.append("phones->").append(phones.length).append("\n");
        buffer.append("mails->").append(mails.length).append("\n");
        return buffer.toString();
    }
}
