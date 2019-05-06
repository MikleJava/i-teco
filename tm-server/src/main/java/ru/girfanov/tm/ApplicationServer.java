package ru.girfanov.tm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.util.SpringJpaConfig;

public class ApplicationServer {
    public static void main(String[] args) {
        final ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringJpaConfig.class);
        final Bootstrap bootstrap = ctx.getBean(Bootstrap.class);
        bootstrap.init();
    }
}
