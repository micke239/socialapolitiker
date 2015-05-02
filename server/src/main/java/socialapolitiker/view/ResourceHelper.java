package socialapolitiker.view;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ResourceHelper {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Value("classpath:/static/rev-manifest.json")
    Resource filePathResource;

    Map<String, String> filePaths;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void getFilePaths() throws JsonParseException, JsonMappingException, IOException {
        if (filePathResource.exists()) {
            filePaths = objectMapper.readValue(filePathResource.getInputStream(), Map.class);
        } else {
            log.warn("Filepath resource not found");
        }
    }

    public String resource(String path) {
        if (filePaths == null) {
            return path;
        }

        String modifiedPath = path;

        // remove first slash
        if (path.startsWith("/")) {
            modifiedPath = modifiedPath.replaceFirst("/", "");
        }

        if (filePaths.containsKey(modifiedPath)) {
            return "/" + filePaths.get(modifiedPath);
        }

        return path;
    }

}
