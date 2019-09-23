package com.demo.entrepreneur.model.mapping.mapper;

public interface Mapper<Data, Entity> {
	Data entityToData(Entity entity);
}
