package com.api.mutants.domain.builder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectBuilder {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param <D>      type of result object.
     * @param <T>      type of source object to map from.
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public <D, T> D map(final T entity, Class<D> outClass) {
        if(entity == null)
            return null;
        return modelMapper.map(entity, outClass);
    }

    /**
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @param <D>        type of objects in result list
     * @param <T>        type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {

        if(CollectionUtils.isEmpty(entityList))
            return null;

        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source      object to map from
     * @param destination object to map to
     */
    public <S, D> D map(final S source, D destination) {

        if(source == null && destination == null)
            return null;
        modelMapper.map(source, destination);
        return destination;
    }

}
