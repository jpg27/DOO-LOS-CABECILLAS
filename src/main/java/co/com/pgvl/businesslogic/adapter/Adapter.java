package co.com.pgvl.businesslogic.adapter;

public interface Adapter<D, T> {
	
	D adaptSource(T data);
	
	T adaptTarget(D data);

}
