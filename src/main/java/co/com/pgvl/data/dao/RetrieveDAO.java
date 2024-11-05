package co.com.pgvl.data.dao;

import java.util.List;

interface RetrieveDAO<T, I> {
	
	T findById(I id);
	
	List<T> findAll();
	
	List<T> findByFilter(T filter);
}
