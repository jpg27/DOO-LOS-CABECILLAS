package co.com.pgvl.crosscutting.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.com.pgvl.crosscutting.exceptions.PGVLApplicationException;
import co.com.pgvl.crosscutting.exceptions.enums.Layer;


public final class SqlConnectionHelper {
	
	private SqlConnectionHelper() {
		
	}
	
	public static boolean connectionIsNull(final Connection connection) {
		return ObjectHelper.isNull(connection);
	}
	
	public static boolean connectionIsOpen(final Connection connection){
		try {
			return !connectionIsNull(connection)&&!connection.isClosed();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de llevar acabo la validacion de si la coneccion estaba o no abierta, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
			
		}
	}
	
	public static void initTransaction(final Connection connection){
		
		validateIfConnectionIsClosed(connection);
		
		try {
			
			if(!connection.getAutoCommit()) {
				var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
				var technicalMessage = "No es posible iniciar una transaccion que ya ha sido iniciada previamente en la base de datos SQL deseada";
				throw new PGVLApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
			}
			
			connection.setAutoCommit(false);
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de iniciar la transaccion, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
			
		}
	}
	
	public static void commitTransaction(final Connection connection){
		
		validateIfConnectionIsClosed(connection);
		validateIfTransactionWasNotInitiated(connection);
		
		try {		
			connection.commit();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de confirmar la transaccion, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
			
		}
	}
	
	public static void rollbackTransaction(final Connection connection){
		
		validateIfConnectionIsClosed(connection);
		validateIfTransactionWasNotInitiated(connection);
		
		try {
			connection.rollback();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de cancelar la transaccion, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
			
		}
	}
	
	public static void validateIfConnectionIsOpen(final Connection connection) {
		if(SqlConnectionHelper.connectionIsOpen(connection)) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "No es tratar de abrir una conexion hacia la base de datos SQL que ya esta abierta";
			throw new PGVLApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
		}
	}
	
	public static void validateIfConnectionIsClosed(final Connection connection) {
		if(!SqlConnectionHelper.connectionIsOpen(connection)) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "La conexion hacia la base de datos SQL esta cerrada, por tanto no es posible llevar a cabo la operacion deseada";
			throw new PGVLApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
		}
	}
	
	public static void validateIfTransactionWasNotInitiated(final Connection connection) {
		try {
			if(connection.getAutoCommit()) {
				var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
				var technicalMessage = "La transaccion no ha sido iniciada previamente para llevar a cabo la operacion deseada en la base de datos SQL deseada";
				throw new PGVLApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
			}
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de validad si la transaccion fue iniciada con la fuente de datos SQL deseada, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}

	}
	
	public static void closeConnection(final Connection connection){
		
		validateIfConnectionIsClosed(connection);
		
		try {
			connection.close();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de cerrar la conexion con la fuente de datos SQL deseada, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
			
		}
	}
	
	public static Connection openConnection(final String connectionString){
		
		try {
			return DriverManager.getConnection(connectionString);

		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada";
			var technicalMessage = "se ha presentado una excepcion de tipo SQLException tratando de obtener la conexion con la fuente de datos SQL deseada, por favor revise el log de errores para tener mas detalles del error presentado";
			throw new PGVLApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
			
		}
	}

}
