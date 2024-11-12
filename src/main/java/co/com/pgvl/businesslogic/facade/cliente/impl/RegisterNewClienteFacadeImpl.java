package co.com.pgvl.businesslogic.facade.cliente.impl;

import co.com.pgvl.businesslogic.adapter.dto.ClienteDTOAdapter;
import co.com.pgvl.businesslogic.facade.cliente.RegisterNewClienteFacade;
import co.com.pgvl.businesslogic.usecase.cliente.impl.RegisterNewClienteImpl;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.exceptions.PGVLException;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.data.dao.enums.DAOSource;
import co.com.pgvl.dto.ClienteDTO;

public class RegisterNewClienteFacadeImpl implements RegisterNewClienteFacade {

	@Override
	public void execute(final ClienteDTO data) {
		var factory = DAOFactory.getFactory(DAOSource.POSTGRESQL);
		
		try {
			factory.initTransaction();
			
			var registerNewClienteUseCase = new RegisterNewClienteImpl(factory);
			var clienteDomain = ClienteDTOAdapter.getClienteDTOAdapter().adaptSource(data);
			
			registerNewClienteUseCase.execute(clienteDomain);
			
			factory.commitTramsaction();
		}catch(final PGVLException exception){
			factory.rollbackTransaction();
			
			throw exception;
		}catch(final Exception exception){
			factory.rollbackTransaction();
			var userMessage = "Se ha presentado un problema tratando de registrar la informacion del neuvo cliente";
			var technicalMessage = "se ha presentado un problema inesperado registrando la informacion del nuevo cliente, por favor revise el log de errores para tener mas detalles";
			
			throw BusinessLogicPGVLException.crear(userMessage, technicalMessage, exception);
		}finally {
			factory.closeConnection();
		}
		
	}

}
