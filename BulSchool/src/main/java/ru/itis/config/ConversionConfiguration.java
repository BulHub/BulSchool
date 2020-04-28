package ru.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import ru.itis.utils.EntityToEntityDtoConverter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfiguration {

    @Bean
    ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new EntityToEntityDtoConverter());
        bean.setConverters(converters);
        return bean;
    }
}
