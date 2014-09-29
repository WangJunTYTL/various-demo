package controllers;

import models.Address;
import models.Car;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.Assert;
import util.Util;
import views.html.*;

/**
 * Created by wangjun on 14-7-14.
 */
public class EbeanDemo extends Controller {

    public static Result findUserById(Long id, int loadType) {
        User user = User.findById(id);
        if (user == null)
            return ok("no user");
        if (loadType == 1)
            Util.report(user.address.province); // 每次执行会查询下数据库
        else if (loadType == 2)
            Util.report(user.cars.size() + "");// 每次执行会查询下数据库
        Util.report(user.carSize + "");
        return ok(user == null ? "no user" : user.username);
    }

    public static Result findUserById2(Long id, int loadType) {
        User user = User.findById(id, loadType);
        return ok(user == null ? "no user" : user.username);
    }


    public static Result findUserByIdLoadAddress(Long id) {
        User user = User.findByIdLoadAddress(id);
        Util.report(user.address.province);
        return ok(user == null ? "no user" : user.username);
    }

    public static Result saveCar() {
        Car car = Form.form(Car.class).bindFromRequest().get();
        Car.saveCar(car);
        return ok("ok...");
    }


    public static Result saveUser(int type) {
        User user = Form.form(User.class).bindFromRequest().get();
        user.saveUser(type);
        return ok("save user success");
    }

    public static Result skip(String page) {
        if (page.equals("saveUser"))
            return ok(ebeanDemo.render(Form.form(User.class).fill(new User())));
        else if (page.equals("L1")) { // L1测试 ，验证是从同一个引用
            User user = User.find();
            if (!Assert.isNull(user))
                return ok(user.cars.size() + "");
            else
                return ok();
        } else if (page.equals("L2")) { // 手动打开L2缓存测试
            Address address = User.findAddress();
            if (!Assert.isNull(address))
                return ok(address.province);
            else
                return ok();
        } else if (page.equals("L22")) { // L2 查询10000次
            long start = System.currentTimeMillis();
            Address address = User.findAddressInL2();
            long end = System.currentTimeMillis();
            Util.report((end - start) + "");
            if (!Assert.isNull(address))
                return ok(address.province);
            else
                return ok();
        } else if (page.equals("L221")) { // L2 查询10000次
            long start = System.currentTimeMillis();
            Address address = User.findAddressInL22();
            long end = System.currentTimeMillis();
            Util.report((end - start) + "");
            if (!Assert.isNull(address))
                return ok(address.province);
            else
                return ok();
        } else if (page.equals("L21")) {// L1 查询10000次
            long start = System.currentTimeMillis();
            Address address = User.findAddressInL1();
            long end = System.currentTimeMillis();
            Util.report((end - start) + "");
            if (!Assert.isNull(address))
                return ok(address.province);
            else
                return ok();
        } else if (page.equals("L222")) {// L2 抓取Address10000次
            long start = System.currentTimeMillis();
            Address address = User.findAddressInL222();
            long end = System.currentTimeMillis();
            Util.report((end - start) + "");
            if (!Assert.isNull(address))
                return ok(address.province);
            else
                return ok();
        } else if (page.equals("L23")) {// 不用 L1 L2 查询10000次
            long start = System.currentTimeMillis();
            Car car = User.findCar();
            long end = System.currentTimeMillis();
            Util.report((end - start) + "");
            if (!Assert.isNull(car))
                return ok(car.name);
            else
                return ok();
        } else if (page.equals("L231")) {// 不用 L1 L2 查询10000次
            long start = System.currentTimeMillis();
            Car car = User.findCar2();
            long end = System.currentTimeMillis();
            Util.report((end - start) + "");
            if (!Assert.isNull(car))
                return ok(car.name);
            else
                return ok();
        } else if (page.equals("saveCar"))
            return ok(carSave.render(Form.form(Car.class).fill(new Car())));
        else if (page.equals("ebeanSummary"))
            return ok(ebeanSummary.render());
        else if (page.equals("jpa"))
            return ok(jpaSummary.render());
        else if (page.equals("autoFetch"))
            return ok(autoFetch.render());
        else
            return status(404, "<h3>你要跳转的页面不存在</h3><hr>", "GBK").as("text/html");
    }


}
