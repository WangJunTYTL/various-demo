package com.peaceful.maven.plugin.demo;

import cn.edaijia.plugin.GreetingMojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/2
 * @since 1.6
 */

public class TestPlugin  extends AbstractMojoTestCase
{
    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        // required for mojo lookups to work
        super.setUp();
    }

    /**
     * @throws Exception
     */
    public void testMojoGoal() throws Exception
    {
//        File testPom = new File( getBasedir(),
//                "src/test/resources/unit/basic-test/basic-test-plugin-config.xml" );
//
//        GreetingMojo mojo = (GreetingMojo) lookupMojo( "yourGoal", testPom );
//
//        assertNotNull( mojo );
    }
}