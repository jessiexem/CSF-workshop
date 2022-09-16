package vttp.csf.miniworkshopserver.configurations;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfiguration implements WebMvcConfigurer {

    private String path;
    private String origin;

    public CORSConfiguration (String p, String o) {
        path = p;
        origin = o;
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
        .addMapping(path)
        .allowedOrigins(origin);
    }
    
}
