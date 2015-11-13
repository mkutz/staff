package com.rewe.digital.staff

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application, args)

        String[] beanNames = applicationContext.beanDefinitionNames
        log.info("Beans provided by Spring Boot:\n\t${beanNames.sort().join("\n\t")}")
    }
}
