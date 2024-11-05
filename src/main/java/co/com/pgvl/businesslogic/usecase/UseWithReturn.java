package co.com.pgvl.businesslogic.usecase;

public interface UseWithReturn<D, R> {
	
	R execute(D data);

}
