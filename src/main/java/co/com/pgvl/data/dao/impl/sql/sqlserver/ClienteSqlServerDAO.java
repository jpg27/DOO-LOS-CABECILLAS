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
    	var countryEntityFilter = new ClienteEntity();
    	countryEntityFilter.setId(id);
    	
    	var result = findByFilter(countryEntityFilter);
    	
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
		var statementWasPrepared = false;

		createSelect(statement);
		createFrom(statement);
		createWhere(statement, filter, parameters);
		createOrderBy(statement);
		
		try (final var preparedStatement = getConnection().prepareStatement(statement.toString())){
			
			for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
				var statementIndex = arrayIndex + 1;
				preparedStatement.setObject(statementIndex, parameters.get(arrayIndex));
			}
			statementWasPrepared = true;
			
			final var result = preparedStatement.executeQuery();
				
			while(result.next()) {
				var countryEntityTmp = new ClienteEntity();
				countryEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
				countryEntityTmp.setNombre(result.getString("name"));
					
				resultSelect.add(countryEntityTmp);
			}
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de llevar a cabo la consulta de los clientes por el filtro deseado, por favor intente de nuevo y si el problema persiste reporte la novedad";
			var technicalMessage = "Se ha presentado un problema al tratar de consultar la informacion de los clientes por el filtro deseado en la base de datos sql server tratando de preparar la sentencia sql que se iba a ejecutar, por favor valide el log de errores para encontrar mayores detalles del problema presentado";

			if(statementWasPrepared) {
				technicalMessage = "Se ha presentado un problema al tratar de consultar la infomracion de los clientes por el filtro deseado en la base de datos sql server tratando de ejecutar la sentencia sql definida, por favor valide el log de errores para encontrar mayores detalles del problema presentado";
			}
			
			throw DataPGVLException.crear(userMessage, technicalMessage, exception);
		}
		
		return resultSelect;
	}
	
	private void createSelect(StringBuilder statement) {
		statement.append("SELECT id, name ");
	}
	
	private void createFrom(StringBuilder statement) {
		statement.append("FROM Cliente");
	}
	
	private void createWhere(StringBuilder statement, final ClienteEntity filter, final List<Object> parameters) {
		if(!ObjectHelper.isNull(filter)) {
			if(!UUIDHelper.isDefault(filter.getId())) {
				statement.append("WHERE id = ? ");
				parameters.add(filter.getId());
			}
			if(!TextHelper.isEmptyApplyingTrim(filter.getNombre())) {
				statement.append((parameters.isEmpty()) ? "WHERE " : "AND ");
				statement.append("name = ? ");
				parameters.add(filter.getNombre());
			}
		}
		
	}
	
	private void createOrderBy(StringBuilder statement) {
		statement.append("ORDER BY name ASC");
	}

	@Override
	public void create(ClienteEntity data) {
	    final var statement = new StringBuilder();
	    statement.append("INSERT INTO Cliente (id, name) VALUES (?, ?)");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setObject(1, UUIDHelper.getDefault());
	        preparedStatement.setString(2, TextHelper.getDefault(data.getNombre()));

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
	    statement.append("UPDATE Cliente SET name = ? WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setString(1, TextHelper.getDefault(data.getNombre()));
	        preparedStatement.setObject(2, data.getId());

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
	    statement.append("DELETE FROM Cliente WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setObject(1, id);

	        preparedStatement.executeUpdate();
	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema al intentar eliminar el cliente. Por favor intente de nuevo.";
	        var technicalMessage = "Error al ejecutar el DELETE en la tabla Cliente en la base de datos SQL Server.";
	        throw DataPGVLException.crear(userMessage, technicalMessage, exception);
	    }
	}


}

