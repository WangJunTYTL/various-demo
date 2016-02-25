package com.peaceful.demo.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.slf4j.helpers.Util;

/**
 * Created by wangjun on 16/2/19.
 */
public class SetUp {

    public static void main(String[] args) {
         /*
     * Guice.createInjector() takes your Modules, and returns a new Injector
     * instance. Most applications will call this method exactly once, in their
     * main() method.
     */
        Injector injector = Guice.createInjector(Stage.PRODUCTION,new ChargeModule());

    /*
     * Now that we've got the injector, we can build objects.
     */
        ChargeBussines chargeBussines = injector.getInstance(ChargeBussines.class);
        chargeBussines.charge(12.12);
        TraceLogI traceLogI = injector.getInstance(TraceLogI.class);
        traceLogI.setScope("change");
        traceLogI = injector.getInstance(TraceLogI.class);
        traceLogI.testScope();

        injector.getInstance(TraceLogI2.class).log("hello world");

        TraceLogI3 traceLogI3 = injector.getInstance(TraceLogI3.class);
        traceLogI3.log("hello world");
        TraceLog3Impl traceLog3I = (TraceLog3Impl) traceLogI3;
        traceLog3I.name = "change";
        traceLogI3 = injector.getInstance(TraceLogI3.class);
        traceLog3I = (TraceLog3Impl) traceLogI3;
        Util.report(traceLog3I.name);

        Test test = injector.getInstance(Test.class);
        test.hello("aa");

        Test2 test2 = injector.getInstance(Test2.class);
        test2.hello("bb");

//        Test3 test3 = injector.getInstance(Test3.class);
//
//        test3.hello("cc");

        Test4 test4 = injector.getInstance(Test4.class);
        test4.hello("dd");

    }
}
