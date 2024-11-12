package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;

import java.util.UUID;
import co.com.pgvl.businesslogic.usecase.cliente.rules.ClienteDoesNotExist;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.entity.ClienteEntity;
import co.com.pgvl.data.dao.DAOFactory;

public final class ClienteDoesNotExistImpl implements ClienteDoesNotExist {

    @Override
    public void execute(final String idString, final DAOFactory factory) {
        validateNotNull(idString);
        UUID id = validateAndConvertUUID(idString);
        validateClienteNotExists(id, factory);
    }
    
    private void validateNotNull(final String idString) {
        if(TextHelper.isEmpty(idString)) {
            var userMessage = "El ID del cliente no puede estar vacío";
            throw BusinessLogicPGVLException.crear(userMessage);
        }
    }
    
    private UUID validateAndConvertUUID(final String idString) {
        try {
            return UUID.fromString(idString);
        } catch (IllegalArgumentException e) {
            var userMessage = "El formato del ID no es válido. Debe ser un UUID válido";
            throw BusinessLogicPGVLException.crear(userMessage);
        }
    }
    
    private void validateClienteNotExists(final UUID id, final DAOFactory factory) {
        ClienteEntity existingCliente = factory.getClienteDAO().findById(id);
        if(existingCliente != null) {
            var userMessage = "Ya existe un cliente registrado con el ID: " + id;
            throw BusinessLogicPGVLException.crear(userMessage);
        }
    }
}