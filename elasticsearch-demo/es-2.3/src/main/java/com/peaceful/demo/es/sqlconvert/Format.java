package com.peaceful.demo.es.sqlconvert;

import com.peaceful.common.util.Util;

/**
 * 格式化sql语句
 * <p/>
 * Created by wangjun on 16/3/14.
 */
public class Format {

    private static char[] keys = {'>', '<', '!', '='};

    private String sql;
    private int index;
    private StringBuffer buffer;
    private char[] cs;


    public Format(String sql) {
        this.sql = sql;
        this.index = 0;
        this.buffer = new StringBuffer();
        this.cs = this.sql.toCharArray();
    }


    public static void main(String[] args) {
        String a = "1= !0 and a";
        Format format = new Format(a);
        Util.report(format.go());

    }

    public String go() {
        while (index < cs.length) {
            if (nextIsKey()) {
                buffer.append(" ");
                buffer.append(cs[index - 1]);
                Character _c = nextChar();
                while (_c != null) {
                    if (_c == ' ') {
                        _c = nextChar();
                        continue;
                    } else if (isKey(_c)) {
                        buffer.append(_c);
                        _c = nextChar();
                    } else {
                        buffer.append(" ");
                        buffer.append(_c);
                        break;
                    }
                }
            } else {
                buffer.append(cs[index - 1]);
            }
        }
        return buffer.toString();
    }

    private boolean isKey(char c) {
        for (char k : keys) {
            if (k == c) {
                return true;
            }
        }
        return false;
    }

    private boolean nextIsKey() {
        if (index < cs.length) {
            for (char k : keys) {
                if (k == cs[index]) {
                    index++;
                    return true;
                }
            }
        }
        index++;
        return false;
    }

    private Character nextChar() {
        if (index < cs.length) {
            return cs[index++];
        }
        return null;
    }

}
