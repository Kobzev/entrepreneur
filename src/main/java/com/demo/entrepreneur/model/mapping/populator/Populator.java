package com.demo.entrepreneur.model.mapping.populator;

public interface Populator<Data, Entity> {
	Entity populateDataToEntity(Data data, Entity entity);
}
