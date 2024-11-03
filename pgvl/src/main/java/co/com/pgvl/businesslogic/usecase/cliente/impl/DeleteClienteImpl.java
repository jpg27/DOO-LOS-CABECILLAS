package co.com.pgvl.businesslogic.usecase.cliente.impl;

import java.util.UUID;

import co.com.pgvl.businesslogic.usecase.cliente.DeleteCliente;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.data.dao.DAOFactory;

public class DeleteClienteImpl implements DeleteCliente {
	
	private DAOFactory daoFactory;
	public DeleteClienteImpl(final DAOFactory daoFactory) {
		//setDaoFactory(daoFactory);
	}

	@Override
	public void execute(UUID data) {
	    // Validar políticas

	    var clienteEntity = daoFactory.getClienteDAO().findById(data);
	    
	    if (ObjectHelper.isNull(clienteEntity)) {
	        var userMessage = "El cliente con el ID especificado no existe.";
	        var technicalMessage = "No se encontró un ClienteEntity con el ID proporcionado en la base de datos.";
	        throw BusinessLogicPGVLException.crear(userMessage, technicalMessage);
	    }

	    daoFactory.getClienteDAO().delete(data);

	}

}
