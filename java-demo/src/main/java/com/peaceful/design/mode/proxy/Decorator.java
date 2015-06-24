package com.peaceful.design.mode.proxy;

import com.peaceful.common.util.Util;

/**
 * Date 14/10/20.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * <p/>
 * 装饰者模式
 */
public class Decorator {

    public static void main(String[] args) {
        DataCreator dataCreator = new DataCreator();
        PublicDataHTMLDecorator dataHTMLDecorator = new PublicDataHTMLDecorator(dataCreator);
        PublicDataHeaderDecorator dataHeaderDecorator = new PublicDataHeaderDecorator(dataHTMLDecorator);
        Util.report(dataHeaderDecorator.publish());
    }
}

//定义功能接口
interface PublicData {
    public String publish();
}

//定义基本功能类
class DataCreator implements PublicData {
    public String publish() {
        return "hello world";
    }
}

//定义装饰者，负责告诉子类，核心业务全权委托给publicData组件完成
abstract class DataDecortor implements PublicData {

    PublicData publicData;

    public DataDecortor(PublicData publicData) {
        this.publicData = publicData;
    }

}

//第一个装饰者
class PublicDataHTMLDecorator extends DataDecortor {

    public PublicDataHTMLDecorator(PublicData publicData) {
        super(publicData);
    }

    @Override
    public String publish() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h1>");
        stringBuilder.append(publicData.publish());
        stringBuilder.append("</h1>");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}

//第二个装饰者
class PublicDataHeaderDecorator extends DataDecortor {

    public PublicDataHeaderDecorator(PublicData publicData1) {
        super(publicData1);
    }

    @Override
    public String publish() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cache-Control:no-cache");
        stringBuffer.append("\n");
        stringBuffer.append(publicData.publish());
        return stringBuffer.toString();
    }
}


