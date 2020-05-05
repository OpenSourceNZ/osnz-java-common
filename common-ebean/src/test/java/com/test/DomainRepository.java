package com.test;

import io.ebean.EbeanServer;
import nz.net.osnz.common.ebean.repository.EbeanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class DomainRepository extends EbeanRepository<MyTestDomain> {

    private static final Logger LOG = LoggerFactory.getLogger(DomainRepository.class);

    @PostConstruct
    public void init() {
        LOG.info("DomainRepository be instantiated");
    }

    @Override
    public Class getEntityClass() {
        return MyTestDomain.class;
    }

}
