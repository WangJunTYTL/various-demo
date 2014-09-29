package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by wangjun on 14-7-15.
 */
@Entity
public class Car extends Model {
    @Id
    public Long id;
    public String name;
    @ManyToMany(mappedBy = "cars")
    public List<User> users;
    public static Finder<Long, Car> find = new Finder(Long.class, Car.class);

    public static void saveCar(List<Car> carList) {
        Ebean.save(carList);
    }

    public static void saveCar(Car car) {
        car.save();
        Ebean.save(car);
    }
}
