package com.finalproject.app.base.repository;

import java.util.List;
import java.util.Map;


public interface Repository<T extends Object> {
	public void create(T newData);

	public void delete(Map<String, String> conditionValuesMap);

	public void update(Map<String, String> fieldAndValuesMap, Map<String, String> conditionValuesMap);
	
	public T get(long uniqueId);
	
	public List<T> get(Map<String, String> conditionValuesMap, long start, long end);
}
