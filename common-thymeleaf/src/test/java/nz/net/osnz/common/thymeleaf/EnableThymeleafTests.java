package nz.net.osnz.common.thymeleaf;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThymeleafConfigTest.class)
public class EnableThymeleafTests {

	@Autowired
	SpringResourceTemplateResolver templateResolver;

	@Autowired
	ThymeleafViewResolver viewResolver;

	@Autowired
	SpringTemplateEngine templateEngine;

	@Test
	public void shouldAutowireAllBeans() {
		Assert.assertNotNull(viewResolver);
		Assert.assertNotNull(templateResolver);
		Assert.assertNotNull(templateEngine);
	}


}
