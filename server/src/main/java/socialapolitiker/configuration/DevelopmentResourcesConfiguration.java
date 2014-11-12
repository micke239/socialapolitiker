package socialapolitiker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Profile("dev")
public class DevelopmentResourcesConfiguration extends WebMvcConfigurerAdapter {

    @Value("${project.home:}")
    private String projectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!this.projectPath.isEmpty()) {
            registry.addResourceHandler("/**").addResourceLocations("file:///" + this.projectPath + "/client/dist/")
                    .setCachePeriod(0);
        }
    }
}
