package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.mixin.UserDTOMixin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Vojtech Prusa
 */
@EnableWebMvc
@Configuration
@Import({ServiceConfiguration.class, RmsWithSampleDataConfiguration.class})
@ComponentScan(basePackages = {"cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers",
        "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.assemblers"})
public class RootWebContext implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(RootWebContext.class);
    private static final String TEXTS = "Texts";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginInterceptor());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    @Primary
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


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping / URI to home view");
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public ViewResolver viewResolver() {
        log.debug("registering JSP in /WEB-INF as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        log.debug("registering ResourceBundle for web Texts");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();

    }

}
