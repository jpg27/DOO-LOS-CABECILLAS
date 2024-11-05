package co.com.pgvl.businesslogic.usecase;

import co.com.pgvl.data.dao.DAOFactory;

public interface RuleWithFactory<T> {
	void execute(T data, DAOFactory factory);

}
