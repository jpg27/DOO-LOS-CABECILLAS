package co.com.pgvl.businesslogic.facade;

public interface FacadeWithReturn<T, R> {
	R execute(T data);

}
