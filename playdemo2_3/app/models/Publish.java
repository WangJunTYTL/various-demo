package models;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 发表作品
 * Created by wangjun on 14-7-25.
 */
@Entity
public class Publish {
    public Long id;
    public String name;
    public Date createTime;
    public Date updateTime;
}
