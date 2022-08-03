package com.ecomerce.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SDGenerator(
        entityPackage = "com.example.demo.model",
        repositoryPackage = "com.example.demo.repositories",
        managerPackage = "com.example.demo.managers",
        repositoryPostfix = "Repository",
        managerPostfix = "Manager",
        onlyAnnotations = false,
        debug = false,
        overwrite = false
)*/

@SpringBootApplication
public class OnlineShipApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShipApplication.class, args);
	}
	
	/*@Bean
	  public ViewResolver viewResolver() {
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setTemplateMode("HTML");
	    templateResolver.setPrefix("templates/");
	    templateResolver.setSuffix(".html");

	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setTemplateResolver(templateResolver);

	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(engine);
	    return viewResolver;
	  } */
}
