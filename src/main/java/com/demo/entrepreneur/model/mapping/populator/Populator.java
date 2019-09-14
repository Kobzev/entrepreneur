package com.demo.entrepreneur.model.mapping.populator;

public interface Populator<DATA, ENTITY> {
	ENTITY populateDataToEntity(DATA data, ENTITY entity);
}
