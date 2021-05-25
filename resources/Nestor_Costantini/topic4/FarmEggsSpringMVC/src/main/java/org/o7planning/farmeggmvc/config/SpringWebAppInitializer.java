package org.o7planning.farmeggmvc.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {
	private static Logger LOG = Logger.getLogger(SpringWebAppInitializer.class);
  public void onStartup(ServletContext servletContext) throws ServletException {
    // TODO Auto-generated method stub
	BasicConfigurator.configure();
	LOG.info("Start Farm!!");
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    appContext.register(AplicationContextConfig.class);

    // Dispatcher Servlet
    ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");

    dispatcher.setInitParameter("contextClass", appContext.getClass().getName());

    servletContext.addListener(new ContextLoaderListener(appContext));

    // UTF8 Character Filter

    FilterRegistration.Dynamic fr =
        servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);

    fr.setInitParameter("encoding", "UTF-8");
    fr.setInitParameter("forceEncoding", "true");
    fr.addMappingForUrlPatterns(null, true, "/*");

  }
}
