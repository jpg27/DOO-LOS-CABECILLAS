package co.com.pgvl.businesslogic.usecase;

public interface RuleWithoutFactory<T> {
	void execute(T data);

}
