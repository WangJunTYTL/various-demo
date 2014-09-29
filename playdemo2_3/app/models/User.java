package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Formula;
import com.avaje.ebean.annotation.Transactional;
import com.avaje.ebean.cache.ServerCacheManager;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import util.Util;

import javax.persistence.*;
import java.util.List;

/**
 * Created by wangjun on 14-3-26.
 */
@Entity // ebean 所用的注解是jpa
//@Table(name = "user")
public class User extends Model {

    @Id //@EmbeddedId 复杂id
    public Long id; //默认命名约定：first_name maps to firstName
    @Constraints.Required
    public String username;
    //@Column(name = "password")
    public String password;
    @ManyToOne(cascade = CascadeType.ALL)
    //会加载address_id ,但不会加载整个address,这个和hibernate有区别，Ebean的延迟加载对于性能的影响不打，默认EAGER，最好打开
    public Address address; //出身地
    /*@OneToOne
    public User mate;//配偶*/
   /* @OneToMany(cascade = CascadeType.PERSIST)
    public List<Address> addresses;//曾居住地  会在address端要求user_id 字段，可以用？ManyToMany代替*/
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
//cascade = CascadeType.ALL 级联情况下会自动插入car个中间表数据,默认不级联 ，对于一些级联更新很是有必要
    public List<Car> cars;
    @Formula(select = "(select count(*) from user_car c where c.user_id = ${ta}.id )")
    // 计算拥有车的数量 ${ta} 代表表的别名 ，还可以写join
    public int carSize;

    //@Transient


    //会满足上述条件后在调用此方法，全局验证
    public String validate() {
        if (username.equals("1")) {
            return "Invalid email or password";
        }
        return null;
    }

   /* public List<ValidationError> validate() {
        if ( username.equals("1")) {
            return null;
        }
        return null;
    }

    public Map<ValidationError> validate() {
        if ( username.equals("1")) {
            return null;
        }
        return null;
    }*/

    public static Finder<Long, User> find = new Finder(Long.class, User.class);

    //============自动抓取测试==start=================
    public static User findById(Long id) {
        User user = find.byId(id); //不抓取car,LAZY不select address_id
        return user;
    }

    public static User findById(Long id, int loadType) {
        User user = find.byId(id); //不抓取car,不抓取address
        String name = null;
        int size = 0;
        if (loadType == 1)
            name = user.address.province; //每次都会加载user
        else if (loadType == 2)
            size = user.cars.size(); //每次都会加载car
        Util.report(name);
        Util.report(size + "");
        return user;
    }


    //连表查询测试
    public static User findByIdLoadAddress(Long id) {
        return find.fetch("address").fetch("cars").where().eq("id", id).findUnique(); // fetch= 生成join查询 不用fetch延迟加载，且不会生成join查询；
    }

    /**
     * *
     * TxType Descriptions
     * TxType	Description
     * REQUIRED	Runs in a Transaction. Will use an existing transaction if it exists, otherwise will create a new Transaction.
     * REQUIRES_NEW	Runs in a new Transaction. If a current transaction exists it will be suspended.
     * MANDATORY	Runs in the existing Transaction. If there is no current existing transaction an exception is thrown.
     * SUPPORTS	Use a transaction if it already exists. If it does not then the method runs without a transaction.
     * NOT_SUPPORTS	Always runs without a transaction. If one already exists then it is suspended.
     * NEVER	Always runs without a transaction. If one already exists then it throws an exception.
     *
     * @param type
     */
    @Transactional// 打开事务，或通过beginTr....  endTr.. 或TxRunnable
    public void saveUser(int type) {
        Util.report("=============saveUser==start===============================");
        if (type == 0) { //不级联情况保存
            this.address.save(); // 保存用户前将用户输入的出生地也保存到数据库
            this.save(); // 保存用户数据
            this.saveManyToManyAssociations("cars"); //保存中间表
        } else if (type == 1) { //如果设置级联car
            this.save(); // 会级联插入car和中间表数据
        }
        Util.report("===============saveUser===end============================");
    }

    public void deleteUser(Long id) {
        Ebean.delete(User.class, id);
    }


    /**
     * *********************other test*********************
     * public void updateUser() {
     * this.update();
     * }
     * <p/>
     * //批量操作
     * public void deleteUser(List<User> users) {
     * Ebean.delete(users);//批量删除
     * Ebean.save(users);//批量保存
     * }
     * <p/>
     * public void deleteUser() {
     * this.delete();
     * }
     * ***************
     */

    //L1 测试 事务级缓存或持久化上下文缓存
    public static User find() {
        Ebean.beginTransaction();
        Car car1 = Car.find.byId(Long.valueOf(7));
        Car car2 = Car.find.byId(Long.valueOf(8));
        User user = User.findById(Long.valueOf(45));
        if (user.cars.get(0).id == 7) { // 查到的ｃａｒ实例属于同一个实例
            Util.report((user.cars.get(0) == car1) + "");
        } else if (user.cars.get(0).id == 8) {
            Util.report((user.cars.get(0) == car2) + "");
        }
        Ebean.endTransaction();
        return user;
    }

    //L2 测试　　bean　cache and query cache
    public static Address findAddress() {//手动指定使用缓存，同时设置为只读，可以在model使用注解指定缓存策略，如果有修改缓存的bean，会自动维持缓存
        Address address_ = Ebean.find(Address.class).setUseCache(true).setReadOnly(true).setId(56).findUnique();
        List<Address> addressList = Ebean.find(Address.class).setUseQueryCache(true).setReadOnly(true).findList();
        Util.report(addressList.size() + "");
        return address_;
    }

    // 测试　不用cache 查询10000次　
    public static Car findCar() {
        Car car = null;
        for (int i = 0; i < 100000; i++) {
            Car car_ = Ebean.find(Car.class, 1);
            Util.report((car == car_) + "");
            car = car_;
        }
        return car;
    }


    // 测试　不用cache 查询10000次　
    public static Car findCar2() {
        Car car = null;
        for (int i = 0; i < 10000; i++) { // 只查1次
            Car car_ = Ebean.getReference(Car.class, 1); // getReference 只会加载创建存在id属性的bean，不会查询数据库，当得到这个bean，用id外的其它属性就会引发查询
            Util.report((car == car_) + "");
            car = car_;
        }
        return car;
    }


    //L1 测试　 查询10000次　
    public static Address findAddressInL1() { // 只查1次
        //      Address address_ = Ebean.find(Address.class, "56");
        Address address = null;
        Ebean.beginTransaction();
        for (int i = 0; i < 10000; i++) {
            Address address_ = Ebean.find(Address.class, 56);
            Util.report((address == address_) + "");
            address = address_;
        }
        Ebean.endTransaction();
        return address;
    }

    //L2 测试　　查询10000次
    public static Address findAddressInL2() {
        Address address = null;
        for (int i = 0; i < 10000; i++) { // 查了数据库
            Address address_ = Ebean.find(Address.class, 56);
//            Address address_ = Ebean.find(Address.class).setUseCache(true).setReadOnly(true).setId(56).findUnique();
            Util.report((address == address_) + "");
            address = address_;
        }
        return address;
    }

    //L2 测试　　查询10000次
    public static Address findAddressInL22() {
        Address address = null;
        for (int i = 0; i < 10000; i++) { // 测试查了数据库
            // Address address_ = Ebean.getReference(Address.class,56);
            Address address_ = Ebean.find(Address.class).setUseCache(true).setReadOnly(true).setId(56).findUnique();
            Util.report((address == address_) + "");
            address = address_;
        }
        return address;
    }

    //L2 测试　　抓取Address10000次  当第一次查询的时候会查询address，之后会从缓存取
    public static Address findAddressInL222() {
        Address address = null;
        for (int i = 0; i < 10000; i++) { // 测试查了数据库
            User user = User.findById(Long.valueOf(41));
            Address address_ = user.address; // 数据从缓存内得到
            Util.report((address == address_) + (address == null ? null : address.province));
            address = address_;
        }
        return address;

    }

    /**
     * **
     * Handling External Modification (
     * via stored procedures etc
     * )
     * When you save/delete beans via Ebean.save() and Ebean.delete() etc Ebean will
     * automatically maintain its cache (removing cached beans and cached queries as
     * appropriate). However, you may often find yourself modifying the database outside of
     * Ebean.
     * For example, you could be using other frameworks, your own JDBC code, stored
     * procedures, batch systems etc. When you do so (and you are using Ebean caching) then
     * you can inform Ebean so that it invalidates appropriate parts of its cache.
     */
    public void test() {
        boolean inserts = true;
        boolean updates = true;
        boolean deletes = false;
        // inform Ebean that some rows have been inserted and updated on address table Ebean will maintain the appropriate caches.
        Ebean.externalModification("address", inserts, updates, deletes);
        // clearAll() caches via the ServerCacheManager ...
        ServerCacheManager serverCacheManager = Ebean.getServerCacheManager();
        // Clear all the caches on the default/primary EbeanServer
        serverCacheManager.clearAll();
        // clear both the bean and query cache for Country beans ...
        serverCacheManager.clear(Address.class);
        // Warm the cache of Country beans
        Ebean.runCacheWarming(Address.class);
    }


    @Override
    public String toString() {
        String result = "";
        if (address == null)
            result.concat("address:null");
        else
            result.concat("address:").concat(address.province).concat("-").concat(address.town);
        return result;
    }

}
