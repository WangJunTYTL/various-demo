package com.wj.hibernate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangjun on 14-4-17.
 */
@Entity
@Table(name = "system")
public class DJSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public String operator;
    public String description;
    @Column(name = "web_index")
    public String webIndex;
    @Column(name = "create_time")
    public Date createTime;
    @Column(name = "update_time")
    public Date updateTime;
    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY)
    public Set<DJUser> users = new HashSet<DJUser>();
    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY)
    public Set<DJMenu> menus = new HashSet<DJMenu>();
    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY)
    public Set<DJResource> resources = new HashSet<DJResource>();
    @OneToMany(mappedBy = "system", fetch = FetchType.LAZY)
    public Set<DJRole> roles = new HashSet<DJRole>();

}
