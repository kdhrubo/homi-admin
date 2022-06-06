package com.tryhomi.admin.core.error;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
@Slf4j
public class ErrorConfigProperties {

    private final ObjectMapper objectMapper;
    @Value("classpath:error.json") Resource resource;
    private List<ErrorConfig> errorConfigList;

    @PostConstruct
    public void load() throws Exception{
        this.errorConfigList =
        objectMapper.readValue(

                resource.getInputStream(), new TypeReference<List<ErrorConfig>>() { });
        log.info("Error list - {}", this.errorConfigList);
    }

    @Data
    public static class ErrorConfig {
        private String url;
        private String exception;
        private String view;
        private String msgKey;
        private List<Attribute> attributes;

        @Data
        public static class Attribute {
            private String key;
            private Object value;
        }
    }
}
