package de.falkmarinov.Internettechnologien.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ThymeleafConfig implements ServletContextListener {

    public static final String TEMPLATE_ENGINE_ATTR = "thymeleafTemplateEngine";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(sce.getServletContext());
        resolver.setPrefix("/WEB-INF/templates");
        resolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        sce.getServletContext().setAttribute(TEMPLATE_ENGINE_ATTR, engine);
    }
}
