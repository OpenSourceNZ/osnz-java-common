package com.test;

import nz.net.osnz.common.ebean.entity.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Entity
public class MyTestDomain {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Long id;

    @Column
    public String title;

}
