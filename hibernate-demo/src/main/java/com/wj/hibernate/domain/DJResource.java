package com.wj.hibernate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by wangjun on 14-4-15.
 */
@Entity(name = "resource")
public class DJResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String pattern;
    public String description;
    public String operator;
    public int isdel;
    @Column(name = "create_time")
    public Date createTime;
    @Column(name = "update_time")
    public Date updateTime;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    public DJSystem system = new DJSystem();
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    public List<DJRole> roles = new ArrayList<DJRole>();

    public DJResource() {

    }


}
