//package ru.girfanov.tm.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//import ru.girfanov.tm.service.UserDetailsServiceImpl;
//
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = "ru.girfanov.tm")
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Bean
//    public InternalResourceViewResolver resolver() {
//        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Bean
//    public UserDetailsService getUserDetailsService(){
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
//        final ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
//        source.addBasenames("classpath:validation.properties");
//        return source;
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//    }
//}
//
