package co.com.pgvl.data.dao.impl.sql.sqlserver;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.com.pgvl.crosscutting.exceptions.DataPGVLException;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;
import co.com.pgvl.data.dao.ClienteDAO;
import co.com.pgvl.data.dao.impl.sql.SqlDAO;
import co.com.pgvl.entity.ClienteEntity;

final class ClienteSqlServerDAO extends SqlDAO implements ClienteDAO {

    public  ClienteSqlServerDAO(Connection connection) {
		super(connection);
	}

    @Override
    public ClienteEntity findById(final UUID id) {
    	var clienteEntityFilter = new ClienteEntity();
    	clienteEntityFilter.setId(id);
    	
    	var result = findByFilter(clienteEntityFilter);
    	
    	return (result.isEmpty()) ? new ClienteEntity() : result.get(0);
    }

	@Override
	public List<ClienteEntity> findAll() {
		return findByFilter(new ClienteEntity());
	}

	@Override
	public List<ClienteEntity> findByFilter(final ClienteEntity filter) {
	    final var statement = new StringBuilder();
	    final var parameters = new ArrayList<>();
	    final var resultSelect = new ArrayList<ClienteEntity>();
	    
	    createSelect(statement);
	    createFrom(statement);
	    createWhere(statement, filter, parameters);
	    createOrderBy(statement);
	    
	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
	            preparedStatement.setObject(arrayIndex + 1, parameters.get(arrayIndex));
	        }
	        
	        final var result = preparedStatement.executeQuery();
	        
	        while(result.next()) {
	            var clienteEntityTmp = new ClienteEntity();
	            clienteEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
	            clienteEntityTmp.setNombre(result.getString("nombre"));
	            clienteEntityTmp.setNumeroDocumento(result.getString("numero_documento"));
	            clienteEntityTmp.setNumeroLicencia(result.getString("numero_licencia"));
	            clienteEntityTmp.setTipoDocumento(result.getString("tipo_documento"));
	            clienteEntityTmp.setCorreo(result.getString("correo"));
	            clienteEntityTmp.setCelular(result.getString("celular"));
	            clienteEntityTmp.setDireccion(result.getString("direccion"));
	            
	            resultSelect.add(clienteEntityTmp);
	        }
	        
	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de llevar a cabo la consulta de los clientes por el filtro deseado, por favor intente de nuevo y si el problema persiste reporte la novedad";
	        var technicalMessage = "Error al ejecutar la consulta SQL en PostgreSQL. Sentencia: " + statement.toString();
	        
	        throw DataPGVLException.crear(userMessage, technicalMessage, exception);
	    }
	    
	    return resultSelect;
	}

	
	private void createSelect(StringBuilder statement) {
		statement.append("SELECT id, nombre, numero_documento, numero_licencia, tipo_documento, correo, celular, direccion ");
	}
	
	private void createFrom(StringBuilder statement) {
		statement.append("FROM Usuarios");
	}
	
	private void createWhere(StringBuilder statement, final ClienteEntity filter, final List<Object> parameters) {
	    boolean hasConditions = false;

	    if (!ObjectHelper.isNull(filter)) {
	        if (!UUIDHelper.isDefault(filter.getId())) {
	            statement.append("WHERE id = ? ");
	            parameters.add(filter.getId());
	            hasConditions = true;
	        }
	        if (!TextHelper.isEmptyApplyingTrim(filter.getNombre())) {
	            statement.append(hasConditions ? "AND " : "WHERE ");
	            statement.append("nombre = ? ");
	            parameters.add(filter.getNombre());
	            hasConditions = true;
	        }
	        if (!TextHelper.isEmptyApplyingTrim(filter.getNumeroDocumento())) {
	            statement.append(hasConditions ? "AND " : "WHERE ");
	            statement.append("numero_documento = ? ");
	            parameters.add(filter.getNumeroDocumento());
	            hasConditions = true;
	        }
	        if (!TextHelper.isEmptyApplyingTrim(filter.getNumeroLicencia())) {
	            statement.append(hasConditions ? "AND " : "WHERE ");
	            statement.append("numero_licencia = ? ");
	            parameters.add(filter.getNumeroLicencia());
	            hasConditions = true;
	        }
	        if (!TextHelper.isEmptyApplyingTrim(filter.getCorreo())) {
	            statement.append(hasConditions ? "AND " : "WHERE ");
	            statement.append("correo = ? ");
	            parameters.add(filter.getCorreo());
	            hasConditions = true;
	        }
	        if (!TextHelper.isEmptyApplyingTrim(filter.getCelular())) {
	            statement.append(hasConditions ? "AND " : "WHERE ");
	            statement.append("celular = ? ");
	            parameters.add(filter.getCelular());
	            hasConditions = true;
	        }
	    }
	}

	
	private void createOrderBy(StringBuilder statement) {
		statement.append("ORDER BY nombre ASC");
	}

	@Override
	public void create(ClienteEntity data) {
	    final var statement = new StringBuilder();
	    statement.append("INSERT INTO Usuarios (id, nombre, numero_documento, numero_licencia, tipo_documento, correo, celular, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setObject(1, UUIDHelper.getDefault());
	        preparedStatement.setString(2, TextHelper.getDefault(data.getNombre()));
	        preparedStatement.setString(3, TextHelper.getDefault(data.getNumeroDocumento()));
	        preparedStatement.setString(4, TextHelper.getDefault(data.getNumeroLicencia()));
	        preparedStatement.setString(5, TextHelper.getDefault(data.getTipoDocumento()));
	        preparedStatement.setString(6, TextHelper.getDefault(data.getCorreo()));
	        preparedStatement.setString(7, TextHelper.getDefault(data.getCelular()));
	        preparedStatement.setString(8, TextHelper.getDefault(data.getDireccion()));

	        preparedStatement.executeUpdate();
	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema al intentar registrar un nuevo cliente. Por favor intente de nuevo.";
	        var technicalMessage = "Error al ejecutar el INSERT en la tabla Cliente en la base de datos SQL Server.";
	        throw DataPGVLException.crear(userMessage, technicalMessage, exception);
	    }
	}

	@Override
	public void update(ClienteEntity data) {
	    final var statement = new StringBuilder();
	    statement.append("UPDATE Usuarios SET nombre = ?, numero_licencia = ?, tipo_documento = ?, correo = ?, celular = ?, direccion = ?  WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setString(1, TextHelper.getDefault(data.getNombre()));
	        preparedStatement.setString(2, TextHelper.getDefault(data.getNumeroLicencia()));
	        preparedStatement.setString(3, TextHelper.getDefault(data.getTipoDocumento()));
	        preparedStatement.setString(4, TextHelper.getDefault(data.getCorreo()));
	        preparedStatement.setString(5, TextHelper.getDefault(data.getCelular()));
	        preparedStatement.setString(6, TextHelper.getDefault(data.getDireccion()));
	        preparedStatement.setObject(7, data.getId());

	        preparedStatement.executeUpdate();
	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema al intentar actualizar el cliente. Por favor intente de nuevo.";
	        var technicalMessage = "Error al ejecutar el UPDATE en la tabla Cliente en la base de datos SQL Server.";
	        throw DataPGVLException.crear(userMessage, technicalMessage, exception);
	    }
	}

	@Override
	public void delete(UUID id) {
	    final var statement = new StringBuilder();
	    statement.append("DELETE FROM Usuarios WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setObject(1, id);

	        preparedStatement.executeUpdate();
	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema al intentar eliminar el cliente. Por favor intente de nuevo.";
	        var technicalMessage = "Error al ejecutar el DELETE en la tabla Cliente en la base de datos SQL Server.";
	        throw DataPGVLException.crear(userMessage, technicalMessage, exception);
	    }
	}

	@Override
	public ClienteEntity findByCorreo(String correo) {
	    var clienteEntityFilter = new ClienteEntity();
	    clienteEntityFilter.setCorreo(correo);
	    
	    var result = findByFilter(clienteEntityFilter);
	    
	    return result.isEmpty() ? null : result.get(0);
    }

}

