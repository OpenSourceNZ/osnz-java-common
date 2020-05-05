package ac.test;

import cn.net.osnz.common.ebean.domain.TestDomain;
import com.test.MyTestDomain;
import io.ebean.EbeanServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestEbeanConfig.class})
public class EbeanUnitTest {

    @Autowired
    private EbeanServer ebeanServer;

    @Before
    public void verifyBean() {
        Assert.assertNotNull(ebeanServer);
    }

    @Test
    public void shouldInstantiateEbeanServerBeanProperly() {
        Assert.assertEquals("default", ebeanServer.getName());
    }

    @Test
    public void shouldSaveAndLoadEntityProperly() {
        TestDomain domain = new TestDomain();
        domain.body = "hello world";
        ebeanServer.save(domain);
        Assert.assertNotNull(domain.id);
        Assert.assertEquals(1, ebeanServer.find(TestDomain.class).findList().size());
        Assert.assertEquals("hello world", ebeanServer.find(TestDomain.class).findOne().body);
    }

    @Test
    public void shouldSaveAndLoadEntityFromOtherPackageProperly() {
        MyTestDomain domain = new MyTestDomain();
        domain.title = "hello world";
        ebeanServer.save(domain);
        Assert.assertNotNull(domain.id);
        Assert.assertEquals(1, ebeanServer.find(MyTestDomain.class).findList().size());
        Assert.assertEquals("hello world", ebeanServer.find(MyTestDomain.class).findOne().title);
    }

}
