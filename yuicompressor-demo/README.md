## buildnumber-maven-plugin & yuicompressor-maven-plugin

利用基于maven的插件yuicompressor压缩js、css，并结合buildnumber为css,js文件名添加版本号



### buildnumber-maven-plugin

buildnumber 插件可以在maven的某个构建生命周期阶段执行（一般生命周期配置在validate阶段），生成构建的版本号，版本号的形式有3种，
1. 如果项目是svn或git管理，可以用当前的管理工具生成的版本号作为插件生成的版本号
1. 另外一种最常用的方式是通过构建项目时的时间戳作为buildnum生成的版本号
1. 还有一种是自己定义版本生成的格式，这种情况需要自己基于buildnum开发下自己的扩展

下面这是一种以构建项目时的时间错作为版本号的方式，此时在maven的整个构建生命周期，会增加一个环境变量，timestamp，如果需要这个变量，可以通过
${timestamp} 获得

    <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>buildnumber-maven-plugin</artifactId>
            <version>1.3</version>
            <executions>
                 <execution>
                     <phase>validate</phase>
                     <goals>
                         <goal>create-timestamp</goal>
                     </goals>
                 </execution>
            </executions>
                 <configuration>
                     <format>{0,date,yyyy-MM-dd HH:mm:ss}</format>
                     <items>
                         <item>timestamp</item>
                     </items>
                 </configuration>
    </plugin>
    

### yuicompressor-maven-plugin

yuicompressor插件可以用来压缩js，css文件，是google公司开发的一款基于maven的插件，可以通过配置在maven的某个构建周期中压缩某些目录下的js，css文件，
一般压缩js和css文件后，我们可能还要解决样式文件在浏览器缓存的问题，这时候，上面介绍的buildnum插件就起到它的作用了，我们用${timestamp}作为压缩后文件的后缀
每次有新的样式文件需要构建时，可以更新这些文件的版本号，下面是部分配置，具体配置可以参考这个项目的pom文件，执行mvn install，可以看到执行压缩的log

    <plugin>
            <groupId>net.alchim31.maven</groupId>
            <artifactId>yuicompressor-maven-plugin</artifactId>
            <version>1.5.0</version>
            <executions>
                <execution>
                    <phase>${assert.compress}</phase>
                    <goals>
                        <goal>compress</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <!--<nosuffix>true</nosuffix>-->
                <suffix>.${timestamp}</suffix>
                <force>true</force>
                <encoding>utf-8</encoding>
                <excludes>
                    <exclude>**/*.pack.js</exclude>
                    <exclude>**/compressed.css</exclude>
                    <exclude>**/*.min.css</exclude>
                    <exclude>**/*.min.js</exclude>
                </excludes>
            </configuration>
    </plugin>
    