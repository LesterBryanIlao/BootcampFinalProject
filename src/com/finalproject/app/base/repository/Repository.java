package com.finalproject.app.base.repository;

interface Repository<T extends Object> {
	public void create(T content);

	public void delete(long id);

	public void update(T newContent);

	public T get(long id);
}
