package cn.edaijia.plugin;

import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/1
 * @since 1.6
 */
@Mojo(name = "sayhi")
// 该插件的goal  mvn groupId:artifactId:version:goal  例如mvn sample.plugin:hello-maven-plugin:1.0-SNAPSHOT:sayhi
public class GreetingMojo extends AbstractMojo {


    //第一种：mvn clean compile -o -Dsayhi.greeting=hello 第一种传参方式
    // 第二种：
    //      <configuration>
    //          <greeting>Welcome</greeting>
    //      </configuration>
    @Parameter(property = "sayhi.greeting", defaultValue = "Hello World!")
    private String greeting;


    /**
     * @parameter expression="${project}"
     * @readonly
     */
    @Parameter(defaultValue="${project}",property = "sayhi.project")
    private MavenProject project;


    public void execute() throws MojoExecutionException {
        getLog().info(greeting);
        try {
            Process process = Runtime.getRuntime().exec("git log -n1");
            InputStream fis = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            StringBuffer cmdout = new StringBuffer();
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                cmdout.append(line).append(System.getProperty("line.separator"));
                if (i == 3) break;
            }
            getLog().info("当前构建的版本信息\n-------------------------------\n" + cmdout.toString());
        } catch (IOException e) {
//            e.printStackTrace();
            getLog().warn("不能读取构建信息，也许你需要配置git的环境变量");
        }

        Build build = project.getBuild();
        String outputDirectory = build.getOutputDirectory();
        String sourceDirectory = build.getSourceDirectory();
        String testOutputDirectory = build.getTestOutputDirectory();
        String testSourceDirectory = build.getTestSourceDirectory();
        getLog().info("\n==========================\nProject build info:");
        String[] info = {outputDirectory, sourceDirectory, testOutputDirectory, testSourceDirectory};
        for (String item : info) {
            getLog().info("\n"+item);
        }
        getLog().info("=======================");
    }


}
