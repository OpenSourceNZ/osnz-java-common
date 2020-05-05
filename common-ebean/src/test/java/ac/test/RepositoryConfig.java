package ac.test;

import com.test.DomainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
public class RepositoryConfig {

    @Bean
    @DependsOn("ebeanServer")
    public DomainRepository domainRepository() {
        return new DomainRepository();
    }

}
