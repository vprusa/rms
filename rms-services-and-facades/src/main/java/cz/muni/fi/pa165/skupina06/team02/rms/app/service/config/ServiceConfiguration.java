package cz.muni.fi.pa165.skupina06.team02.rms.app.service.config;

import cz.muni.fi.pa165.skupina06.team02.rms.app.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.ShoppingItemDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingItem;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.ShoppingList;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {HouseholdServiceImpl.class, ShoppingItemServiceImpl.class,
        ShoppingListServiceImpl.class, UserServiceImpl.class, BeanMappingServiceImpl.class})
public class ServiceConfiguration {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerConfig());
        return dozer;
    }

    public class DozerConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Household.class, HouseholdDTO.class);
            mapping(ShoppingItem.class, ShoppingItemDTO.class);
            mapping(ShoppingList.class, ShoppingItemDTO.class);
            mapping(User.class, UserDTO.class);
        }
    }

}
