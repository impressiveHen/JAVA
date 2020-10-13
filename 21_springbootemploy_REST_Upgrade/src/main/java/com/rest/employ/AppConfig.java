package com.rest.employ;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
@Configuration:
Annotating a class with the @Configuration annotation indicates that the class
will be used by JavaConfig as a source of bean definitions. Configuration can be
considered the equivalent of XML's <beans/> element.

@Bean:
Is a method-level annotation and a direct analog of the XML <bean/> element.
When JavaConfig encounters such a method, it will execute that method and register
the return value as a bean within a BeanFactory. By default, the bean name will be
that of the method name
*/
@Configuration
public class AppConfig {
    /*
	@Bean: It decouples the declaration of the bean from the class definition and
	lets you create and configure beans exactly how you choose. With @Bean you aren't
	placing this annotation at the class level.

	will result in a bean named "restTemplate" being available in the
	BeanFactory/ApplicationContext, bound to an object instance of type
	RestTemplate

	equivalent to
	<beans>
        <bean name="restTemplate" class="RestTemplate"/>
    </beans>
	*/

//    RestTemplate class is a synchronous client that is designed to call REST services.
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
