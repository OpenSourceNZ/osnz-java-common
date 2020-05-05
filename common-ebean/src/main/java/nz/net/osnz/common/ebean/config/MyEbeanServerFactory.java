package nz.net.osnz.common.ebean.config;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.avaje.agentloader.AgentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class MyEbeanServerFactory implements FactoryBean<EbeanServer> {
    private static final Logger LOG = LoggerFactory.getLogger(MyEbeanServerFactory.class);

    @Autowired
    private MyEbeanDataSourceConfig myDataSourceConfig;

    @Autowired(required = false)
    private DataSource dataSource;

    @PostConstruct
    public void loadAgent() {
        if (myDataSourceConfig.isEnableEbeanAgent()) {
            // Load the agent into the running JVM process
            StringBuilder ebeanAgent = new StringBuilder();
            ebeanAgent.append("debug=").append(myDataSourceConfig.getEbeanDebug());
            if (!StringUtils.isEmpty(myDataSourceConfig.getEbeanPackages())) {
                ebeanAgent.append(";packages=").append(myDataSourceConfig.getEbeanPackages());
            } else if (!StringUtils.isEmpty(myDataSourceConfig.getEbeanSearchPackages())) {
                ebeanAgent.append(";packages=").append(myDataSourceConfig.getEbeanSearchPackages());
            }
            if (!AgentLoader.loadAgentFromClasspath("ebean-agent", ebeanAgent.toString())) {
                LOG.info("ebean-agent not found in classpath - not dynamically loaded");
            }
        } else {
            LOG.info("Disabled load Ebean agent");
        }
    }

    public EbeanServer getObject() throws Exception {
        return createEbeanServer();
    }

    public Class<?> getObjectType() {
        return EbeanServer.class;
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * Create a EbeanServer instance.
     *
     * @return a newly instantiated {@link EbeanServer} object
     */
    public EbeanServer createEbeanServer() {
        ServerConfig config = new ServerConfig();
        config.setName("default");
        config.setDefaultServer(true);
        config.setRegister(true);
        if (dataSource == null) {
            config.setDataSourceConfig(myDataSourceConfig);
        } else {
            config.setDataSource(dataSource);
        }
        config.loadFromProperties(loadEbeanProperties(myDataSourceConfig));
        config.setDdlGenerate(myDataSourceConfig.isEbeanDdlGenerate());
        config.setDdlRun(myDataSourceConfig.isEbeanDdlRun());
        return EbeanServerFactory.create(config);
    }

    /**
     * Load all reachable properties
     *
     * @param config is a reference of {@link MyEbeanDataSourceConfig}
     * @return a {@link Properties} contains all reachable properties
     */
    protected static Properties loadEbeanProperties(MyEbeanDataSourceConfig config) {
        Properties ebeanProperties = new Properties();
        ebeanProperties.put("ebean.ddl.generate", Boolean.toString(config.isEbeanDdlGenerate()));
        ebeanProperties.put("ebean.ddl.run", Boolean.toString(config.isEbeanDdlRun()));
        if (!StringUtils.isEmpty(config.getEbeanPackages())) {
            ebeanProperties.put("ebean.packages", config.getEbeanPackages());
        } else if (!StringUtils.isEmpty(config.getEbeanSearchPackages())) {
            ebeanProperties.put("ebean.packages", config.getEbeanSearchPackages());
        }
        return ebeanProperties;
    }

}
