package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author github.com/fi-muni/PA165
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>(objects.size());

        for (Object obj : objects) {
            mappedCollection.add(mapTo(obj, mapToClass));
        }

        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}
