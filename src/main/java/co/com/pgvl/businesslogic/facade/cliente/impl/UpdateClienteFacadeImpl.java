package co.com.pgvl.businesslogic.facade.cliente.impl;

import co.com.pgvl.businesslogic.adapter.dto.ClienteDTOAdapter;
import co.com.pgvl.businesslogic.facade.cliente.UpdateClienteFacade;
import co.com.pgvl.businesslogic.usecase.cliente.impl.UpdateClienteImpl;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.exceptions.PGVLException;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.data.dao.enums.DAOSource;
import co.com.pgvl.dto.ClienteDTO;

public class UpdateClienteFacadeImpl implements UpdateClienteFacade {

	@Override
	public void execute(ClienteDTO data) {
        var factory = DAOFactory.getFactory(DAOSource.POSTGRESQL);

        try {
            factory.initTransaction();

            var clienteDomain = ClienteDTOAdapter.getClienteDTOAdapter().adaptSource(data);

            var updateClienteUseCase = new UpdateClienteImpl(factory);
            updateClienteUseCase.execute(clienteDomain);

            factory.commitTramsaction();
        } catch (final PGVLException exception) {
            factory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            factory.rollbackTransaction();
            var userMessage = "Se ha presentado un problema al intentar actualizar la información del cliente.";
            var technicalMessage = "Ocurrió un error inesperado al intentar actualizar la información del cliente. Verifique el log para más detalles.";
            throw BusinessLogicPGVLException.crear(userMessage, technicalMessage, exception);
        } finally {
            factory.closeConnection();
        }
		
	}

}
