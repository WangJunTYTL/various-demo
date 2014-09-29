import actor.MyActor;
import actor.MyThread;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Application;
import play.Configuration;
import play.GlobalSettings;
import play.Mode;
import play.api.mvc.EssentialFilter;
import play.api.mvc.Handler;
import play.libs.Akka;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.SimpleResult;
import scala.concurrent.duration.FiniteDuration;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void beforeStart(Application application) {
        logger.debug("beforeStart...");
        super.beforeStart(application);
    }

    @Override
    public F.Promise<Result> onError(Http.RequestHeader requestHeader, Throwable throwable) {
        logger.debug("onError...");
        return super.onError(requestHeader, throwable);
    }

    //每次请求
    @Override
    public Action onRequest(Http.Request request, Method method) {
        logger.debug("onRequest...");
        return super.onRequest(request, method);
    }

    //包括响应
    @Override
    public Handler onRouteRequest(Http.RequestHeader requestHeader) {
        logger.debug("onRouteRequest...");
        return super.onRouteRequest(requestHeader);
    }

    //程序启动
    public void onStart(Application app) {
        logger.debug("onStart...");
        ActorRef instance = Akka.system().actorOf(Props.create(MyActor.class));
        FiniteDuration.create(2, TimeUnit.SECONDS);
        Akka.system().scheduler().schedule(FiniteDuration.Zero(),FiniteDuration.create(2, TimeUnit.SECONDS),instance,"AdOrderScheduling", Akka.system().dispatcher(),null);
        //创建线程
        Akka.system().scheduler().schedule(FiniteDuration.Zero(),FiniteDuration.create(2, TimeUnit.SECONDS),new MyThread(),Akka.system().dispatcher());
    }

    @Override
    public <A> A getControllerInstance(Class<A> aClass) throws Exception {
        logger.debug("onStart...");
        return super.getControllerInstance(aClass);
    }

    @Override
    public F.Promise<Result> onBadRequest(Http.RequestHeader requestHeader, String s) {
        logger.debug("onBadRequest...");
        return super.onBadRequest(requestHeader, s);
    }

    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        logger.debug("filters...");
        return super.filters();
    }

    @Override
    public Configuration onLoadConfig(Configuration configuration, File file, ClassLoader classLoader, Mode mode) {
        logger.debug("onLoadConfig...");
        return super.onLoadConfig(configuration, file, classLoader, mode);
    }

    @Override
    public Configuration onLoadConfig(Configuration configuration, File file, ClassLoader classLoader) {
        logger.debug("onLoadConfig...");
        return super.onLoadConfig(configuration, file, classLoader);
    }

    @Override
    public F.Promise<Result> onHandlerNotFound(Http.RequestHeader requestHeader) {
        logger.debug("onHandlerNotFound...");
        return super.onHandlerNotFound(requestHeader);
    }

    @Override
    public void onStop(Application application) {
        logger.debug("onStop...");
    }
}