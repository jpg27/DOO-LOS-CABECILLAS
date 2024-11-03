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
		var connectionString = "jdbc:sqlserver://ucobet-server.database.windows.net:1433;database=ucobet-db;user=ucobetdbuser;password=uc0b3tdbus3r!;encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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
