package ru.girfanov.tm.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.girfanov.tm.endpoint.*;

@Configuration
@ComponentScan("ru.girfanov.tm")
public class EndPointProducerUtil {

    @Bean
    public ProjectEndPoint getProjectEndPoint() {
        return new ProjectEndPointService().getProjectEndPointPort();
    }

    @Bean
    public TaskEndPoint getTaskEndPoint() {
        return new TaskEndPointService().getTaskEndPointPort();
    }

    @Bean
    public UserEndPoint getUserEndPoint() {
        return new UserEndPointService().getUserEndPointPort();
    }

    @Bean
    public SessionEndPoint getSessionEndPoint() {
        return new SessionEndPointService().getSessionEndPointPort();
    }

    @Bean
    public DataDomainEndPoint getDataDomainEndPoint() { return new DataDomainEndPointService().getDataDomainEndPointPort(); }

}
