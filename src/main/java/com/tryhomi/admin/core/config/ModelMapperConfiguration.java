package com.tryhomi.admin.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

   @Bean
   public ModelMapper modelMapper() {

      ModelMapper modelMapper =  new ModelMapper();
      //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      //modelMapper.getConfiguration().setAmbiguityIgnored(true);
      //modelMapper.getConfiguration().setFieldMatchingEnabled(true);
      //modelMapper.getConfiguration() .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
      return modelMapper;
   }
}
