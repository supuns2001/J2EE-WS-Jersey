package lk.jiat.app.jersey.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

//@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {

    public AppConfig(){
        packages("lk.jiat.app.jersey.controller");
        packages("lk.jiat.app.jersey.middleware");

        register(JspMvcFeature.class);

        property(JspMvcFeature.TEMPLATE_BASE_PATH,"/WEB-INF/views");


    }



}
