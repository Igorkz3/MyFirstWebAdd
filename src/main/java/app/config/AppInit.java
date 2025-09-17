package app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {   // Конфигурация с root контекстом
        return new Class<?>[] {HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { // Конфигурация spring mvc
        return new Class<?>[] {WebConfig.class};
    }


    @Override
    protected String[] getServletMappings() { // маппинг
        return new String[] { "/" };
    }
}