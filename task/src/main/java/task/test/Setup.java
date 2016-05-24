package task.test;

import cn.edaijia.task.context.TaskSystem;

/**
 * @author WangJun
 * @version 1.0 16/3/30
 */
public class Setup {

    public static void main(String[] args) {
        Hello hello = TaskSystem.registerASyncClass(Hello.class);
        hello.say("hello world");
    }
}
