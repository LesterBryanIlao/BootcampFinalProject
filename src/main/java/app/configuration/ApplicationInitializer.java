package app.configuration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextCleanupListener;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(ApplicationConfiguration.class);
        ctx.register(JpaConfiguration.class);

        container.addListener(new ContextLoaderListener(ctx));
        container.addListener(new ContextCleanupListener());
        container.addListener(new SessionListener());

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcherServlet);
        servlet.setLoadOnStartup(0);
        servlet.setAsyncSupported(true);
        servlet.addMapping("/app/*");
    }

    public class SessionListener implements HttpSessionListener {

        @Override
        public void sessionCreated(HttpSessionEvent event) {
            event.getSession().setMaxInactiveInterval(1800);
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent event) {
        }

    }

}
