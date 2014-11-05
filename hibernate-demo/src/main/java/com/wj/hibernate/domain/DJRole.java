package com.wj.hibernate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by wangjun on 14-4-15.
 */
@Entity(name = "role")
public class DJRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public String operator;
    public int isdel;
    @Column(name = "create_time")
    public Date createTime;
    @Column(name = "update_time")
    public Date updateTime;
    @ManyToOne(cascade = {CascadeType.MERGE})
    public DJSystem system = new DJSystem();
    @ManyToMany(mappedBy = "roles")
    public Set<DJUser> users = new HashSet<DJUser>();
    @ManyToMany(mappedBy = "roles")
    public Set<DJMenu> menus = new HashSet<DJMenu>();
    @ManyToMany(mappedBy = "roles")
    public Set<DJResource> resources = new HashSet<DJResource>();

    public DJRole() {

    }

}
