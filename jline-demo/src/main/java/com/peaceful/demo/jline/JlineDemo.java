package com.peaceful.demo.jline;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;

import java.io.IOException;
import java.util.List;

/**
 * Created by wangjun on 15/11/24.
 */
public class JlineDemo {

    public static void main(String[] args) throws IOException {
        ConsoleReader reader = new ConsoleReader();
        reader.addCompleter(new MyCompleter());
        String line = null;
        do {
            line = reader.readLine("input>");// 每次输入都加上的前缀
            if (line != null) {
                //TODO
//                Util.report(line);
                exe(line);
            }
        }
        while (line != null && !line.equals("exist"));
    }

    static class MyCompleter implements Completer {
        @Override
        public int complete(String buffer, int cursor, List candidates) {
            if (buffer.length() > 0 && cursor > 0) {
                String substring = buffer.substring(0, cursor);
                if (substring.endsWith("my")) {
                    candidates.add("myfolder");
                    candidates.add("myfile");
                    return cursor - 2;
                }
            }
            return cursor;
        }
    }

    static void exe(String cmd){
        if (cmd.equals("?")){
            System.err.println("hello world");
        }
    }
}
