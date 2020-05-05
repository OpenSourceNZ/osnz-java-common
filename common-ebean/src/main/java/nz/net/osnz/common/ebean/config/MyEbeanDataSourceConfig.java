package nz.net.osnz.common.ebean.config;


import io.ebean.datasource.DataSourceConfig;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class MyEbeanDataSourceConfig extends DataSourceConfig {

    @Value("${ebean.debug:1}")
    private int ebeanDebug = 1;

    @Value("${spring.datasource.driver:com.mysql.jdbc.Driver}")
    private String myDriver;

    @Value("${spring.datasource.username:}")
    private String myUsername;

    @Value("${spring.datasource.password:}")
    private String myPassword;

    @Value("${spring.datasource.url:}")
    private String myDataSourceUrl;

    @Value("${ebean.ddl.generate:false}")
    private boolean ebeanDdlGenerate = false;

    @Value("${ebean.ddl.run:false}")
    private boolean ebeanDdlRun = false;

    @Value("${ebean.packages:}")
    private String ebeanPackages;

    @Value("${ebean.search.packages:}")
    private String ebeanSearchPackages;

    @Value("${ebean.enableAgent:true}")
    private boolean enableEbeanAgent = true;

    @Value("${spring.datasource.minConnection:4}")
    private int defaultMinConnection = 4;

    @Value("${spring.datasource.maxConnection:25}")
    private int defaultMaxConnection = 25;

    public MyEbeanDataSourceConfig() {
        super();
    }

    @PostConstruct
    void init() {
        this.setMinConnections(defaultMinConnection);
        this.setMaxConnections(defaultMaxConnection);
        this.setHeartbeatSql("select count(*) from dual");
        this.setDriver(myDriver);
        this.setUsername(myUsername);
        this.setPassword(myPassword);
        this.setUrl(myDataSourceUrl);
    }

    public int getEbeanDebug() {
        return ebeanDebug;
    }

    public String getMyDriver() {
        return myDriver;
    }

    public String getMyUsername() {
        return myUsername;
    }

    public String getMyPassword() {
        return myPassword;
    }

    public String getMyDataSourceUrl() {
        return myDataSourceUrl;
    }

    public boolean isEbeanDdlGenerate() {
        return ebeanDdlGenerate;
    }

    public boolean isEbeanDdlRun() {
        return ebeanDdlRun;
    }

    public String getEbeanPackages() {
        return ebeanPackages;
    }

    public String getEbeanSearchPackages() {
        return ebeanSearchPackages;
    }

    public boolean isEnableEbeanAgent() {
        return enableEbeanAgent;
    }

    public int getDefaultMinConnection() {
        return defaultMinConnection;
    }

    public int getDefaultMaxConnection() {
        return defaultMaxConnection;
    }

}
