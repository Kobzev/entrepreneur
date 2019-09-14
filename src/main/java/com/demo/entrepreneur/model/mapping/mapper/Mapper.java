package com.demo.entrepreneur.model.mapping.mapper;

public interface Mapper<DATA, ENTITY> {
	ENTITY dataToEntity(DATA data);
	DATA entityToData(ENTITY entity);
}
