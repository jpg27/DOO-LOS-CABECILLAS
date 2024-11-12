package co.com.pgvl.businesslogic.usecase.cliente.rules.impl;


import co.com.pgvl.businesslogic.usecase.cliente.rules.CorreoClienteIsUnique;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.data.dao.DAOFactory;

public final class CorreoClienteIsUniqueImpl implements CorreoClienteIsUnique {
    @Override
    public void execute(final String correo, final DAOFactory factory) {
        validateNotNull(correo);
        validateCorreoNotExists(correo, factory);
    }
    
    private void validateNotNull(final String correo) {
        if(TextHelper.isEmpty(correo)) {
            var userMessage = "El correo electrónico no puede estar vacío";
            throw BusinessLogicPGVLException.crear(userMessage);
        }
    }
    
    private void validateCorreoNotExists(final String correo, final DAOFactory factory) {
        var existingCliente = factory.getClienteDAO().findByCorreo(correo);
        
        if(existingCliente != null) {
            var userMessage = "Ya existe un cliente registrado con el correo electrónico: " + correo;
            throw BusinessLogicPGVLException.crear(userMessage);
        }
    }
}


