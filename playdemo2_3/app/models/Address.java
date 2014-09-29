package models;

import com.avaje.ebean.annotation.CacheStrategy;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 地址
 * Created by wangjun on 14-3-26.
 */
//@CacheStrategy(useBeanCache = true)//指定缓存策略
@Entity
public class Address extends Model {
    @Id
    public Long id;
    public String province; // 省
    public String town; // 市
}
