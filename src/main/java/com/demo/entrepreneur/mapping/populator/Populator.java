package com.demo.entrepreneur.mapping.populator;

public interface Populator<D, E> {

    E populateDataToEntity(D data, E entity);

    E populateDataToEntity(D data);
}
