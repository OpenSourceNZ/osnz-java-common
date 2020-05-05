package nz.net.osnz.common.thymeleaf;

import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable Thymeleaf template engine
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@SuppressWarnings("deprecation")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportResource("classpath*:/META-INF/osnz/spring-thymeleaf.xml")
public @interface EnableThymeleaf {

}
