package com.TestDemo.config;

import com.TestDemo.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ImportResource(locations =  {"classpath:person.properties"})
public class TestConfig {
    @Bean
    public Person Person(@Value("last-name")String name){
        System.out.println(name);
        return new Person();
    }
}
