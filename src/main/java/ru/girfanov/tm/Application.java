package ru.girfanov.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.girfanov.tm.service.UserDetailsServiceImpl;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
        final ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.addBasenames("classpath:validation.properties");
        return source;
    }
}