package ac.test;

import com.test.DomainRepository;
import com.test.MyTestDomain;
import io.ebean.EbeanServer;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestEbeanConfig.class, RepositoryConfig.class})
public class DomainRepositoryTest {

    @Autowired
    private EbeanServer ebeanServer;

    @Autowired
    private DomainRepository domainReporsitory;

    @Before
    public void verifyBean() {
        Assert.assertNotNull(domainReporsitory);
        Assert.assertNotNull(ebeanServer);
    }

    @Test
    public void shouldInsertDataProperly() {

        MyTestDomain myTestDomain = new MyTestDomain();
        myTestDomain.id = 1L;
        myTestDomain.title = "new bean";
        int oldCount = domainReporsitory.countAll();
        Assert.assertEquals(0, oldCount);
        domainReporsitory.save(myTestDomain);
        int newCount = domainReporsitory.countAll();
        Assert.assertEquals(1, newCount - oldCount);
        Assert.assertSame(1L, myTestDomain.id);
    }

}
