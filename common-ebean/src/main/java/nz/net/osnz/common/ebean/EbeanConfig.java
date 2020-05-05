package nz.net.osnz.common.ebean;

import io.ebean.EbeanServer;
import nz.net.osnz.common.ebean.config.MyEbeanDataSourceConfig;
import nz.net.osnz.common.ebean.config.MyEbeanServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
public class EbeanConfig {

    @Bean
    public MyEbeanDataSourceConfig myEbeanDataSourceConfig() {
        return new MyEbeanDataSourceConfig();
    }

    @Bean("myEbeanServerFactory")
    public MyEbeanServerFactory myEbeanServerFactory() {
        return new MyEbeanServerFactory();
    }

    @Bean
    public EbeanServer ebeanServer() {
        try {
            return myEbeanServerFactory().getObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
