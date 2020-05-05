package cn.net.osnz.common.ebean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Entity
public class TestDomain {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column
    public String body;

}
