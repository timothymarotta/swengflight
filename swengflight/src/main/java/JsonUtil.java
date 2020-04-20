import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {
    public static String toJsonString(Object objectToSerialize) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new ParanamerModule());
        return mapper.writeValueAsString( objectToSerialize);
    }

    public static void toJsonFile(String filename, Object objectToSerialize) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new ParanamerModule());
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(new File(filename), objectToSerialize);
    }

    public static <T> T fromJsonFile(String filename, Class<? extends T> classToBeCreated) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParanamerModule());
        mapper.registerModule(new JavaTimeModule());
        return  mapper.readValue(new File(filename), classToBeCreated);
    }

    public static <T> List<T> listFromJsonFile(String filename, Class<? extends T> classToBeCreated) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //found this fix here: https://stackoverflow.com/questions/11659844/jackson-deserialize-generic-class-variable
        return  mapper.readValue(new File(filename), mapper.getTypeFactory().constructParametricType(List.class, classToBeCreated));
    }
}

