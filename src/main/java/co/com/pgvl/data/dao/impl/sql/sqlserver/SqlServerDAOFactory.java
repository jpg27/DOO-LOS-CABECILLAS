package co.com.pgvl.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

import co.com.pgvl.crosscutting.helpers.SqlConnectionHelper;
import co.com.pgvl.data.dao.ClienteDAO;
import co.com.pgvl.data.dao.DAOFactory;

public final class SqlServerDAOFactory extends DAOFactory {
	
	private Connection connection;
	
	public SqlServerDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
	    SqlConnectionHelper.validateIfConnectionIsOpen(connection);
	    var connectionString = "jdbc:postgresql://localhost:5432/clientes?user=postgres&password=1234";
	    connection = SqlConnectionHelper.openConnection(connectionString);
	}

	@Override
	public void initTransaction() {
		SqlConnectionHelper.initTransaction(connection);
		
	}

	@Override
	public void commitTramsaction() {
		SqlConnectionHelper.commitTransaction(connection);
		
	}

	@Override
	public void rollbackTransaction() {
		SqlConnectionHelper.rollbackTransaction(connection);
		
	}

	@Override
	public void closeConnection() {
		SqlConnectionHelper.closeConnection(connection);
		
	}

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
