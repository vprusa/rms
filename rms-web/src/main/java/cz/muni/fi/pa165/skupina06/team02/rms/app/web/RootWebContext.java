package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.mixin.UserDTOMixin;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.config.ServiceConfiguration;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Vojtech Prusa
 *
 */
//@EnableWebMvc
@Configuration
@Import({ ServiceConfiguration.class, RmsWithSampleDataConfiguration.class/*, WebSecurityConfig.class */})
@ComponentScan(basePackages = { "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers",
        "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.assemblers", "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest" })
public class RootWebContext implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new AllowOriginInterceptor());
    }
    /*
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        return manager;
    }*/

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //configurer.enable();
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login");//.setViewName("login");
    }
    
    @Bean
    //@Primary
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));

        objectMapper.addMixIn(UserDTO.class, UserDTOMixin.class);

        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }
    
}
