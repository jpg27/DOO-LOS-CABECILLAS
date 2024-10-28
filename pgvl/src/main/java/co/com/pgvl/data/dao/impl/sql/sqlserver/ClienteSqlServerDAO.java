package co.com.pgvl.data.dao.impl.sql.sqlserver;

//import java.lang.reflect.Array;
import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
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

	//private static final String FIND_BY_ID_QUERY = "SELECT id, name FROM Country WHERE id = ?";

    @Override
    public ClienteEntity findById(final UUID id) {
    	var countryEntityFilter = new ClienteEntity();
    	countryEntityFilter.setId(id);
    	
    	var result = findByFilter(countryEntityFilter);
    	
    	return (result.isEmpty()) ? new ClienteEntity() : result.get(0);
        /*CountryEntity country = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {

            statement.setString(1, id.toString());

            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    country = mapResultSetToCountryEntity(resultSet);
                }
            }

        } catch (SQLException e) {
            logError(e, id); 
            throw new RuntimeException("Error al buscar el país con id: " + id, e);
        }

        return country;
    }

    private CountryEntity mapResultSetToCountryEntity(ResultSet resultSet) throws SQLException {
        return new CountryEntity(
        );
    }

 
    private void logError(SQLException e, UUID id) {
        System.err.println("Error al consultar el país con id: " + id);
        e.printStackTrace();*/
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
			var userMessage = "Se ha presentado un problema tratando de llevar a cabo la consulta de los paises por el filtro deseado, por favor intente de nuevo y si el problema persiste reporte la novedad";
			var technicalMessage = "Se ha presentado un problema al tratar de consultar la infomracion de los paises por el filtro deseado en la base de datos sql server tratando de preparar la sentencia sql que se iba a ejecutar, por favor valide el log de errores para encontrar mayores detalles del problema presentado";

			if(statementWasPrepared) {
				technicalMessage = "Se ha presentado un problema al tratar de consultar la infomracion de los paises por el filtro deseado en la base de datos sql server tratando de ejecutar la sentencia sql definida, por favor valide el log de errores para encontrar mayores detalles del problema presentado";
			}
			
			throw DataPGVLException.crear(userMessage, technicalMessage, exception);
		}
		
		return resultSelect;
	}
	
	private void createSelect(StringBuilder statement) {
		statement.append("SELECT id, name ");
	}
	
	private void createFrom(StringBuilder statement) {
		statement.append("FROM Country");
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
}

