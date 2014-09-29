package action;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.SimpleResult;
import util.Util;

/**
 * Created by wangjun on 14-7-3.
 */
public class MyAction  extends Action.Simple{
    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {
        Util.report("action is working");
        return delegate.call(context);
    }

/*    public F.Promise<SimpleResult> call(Http.Context context) throws Throwable {

    }*/
}
