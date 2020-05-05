package ac.test;

import nz.net.osnz.common.ebean.EbeanConfig;
import org.avaje.agentloader.AgentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@PropertySource("classpath:application-test.properties")
@Import(EbeanConfig.class)
public class TestEbeanConfig {

}
