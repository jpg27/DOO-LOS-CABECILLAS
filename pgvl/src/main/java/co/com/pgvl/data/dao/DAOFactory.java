package co.com.pgvl.data.dao;

import co.com.pgvl.data.dao.enums.DAOSource;

public abstract class DAOFactory {
	
	public final static DAOFactory getFactory(final DAOSource source) {
		return null;
	}
	
	protected abstract void openConnection();
	
	public abstract void initTransaction();
	
	public abstract void commitTramsaction();
	
	public abstract void rollbackTransaction();
	
	public abstract void closeConnection();
	
	public abstract ClienteDAO getClienteDAO();

}
