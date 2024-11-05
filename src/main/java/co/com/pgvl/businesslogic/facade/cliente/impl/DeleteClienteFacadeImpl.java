package co.com.pgvl.businesslogic.facade.cliente.impl;

import java.util.UUID;

import co.com.pgvl.businesslogic.facade.cliente.DeleteClienteFacade;
import co.com.pgvl.businesslogic.usecase.cliente.impl.DeleteClienteImpl;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.exceptions.PGVLException;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.data.dao.enums.DAOSource;

public class DeleteClienteFacadeImpl implements DeleteClienteFacade {

	@Override
	public void execute(UUID data) {
        var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);

        try {
            factory.initTransaction();

            var deleteClienteUseCase = new DeleteClienteImpl(factory);
            deleteClienteUseCase.execute(data);

            factory.commitTramsaction();
        } catch (final PGVLException exception) {
            factory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception) {
            factory.rollbackTransaction();
            var userMessage = "Se ha presentado un problema al intentar eliminar el cliente.";
            var technicalMessage = "Ocurrió un error inesperado al intentar eliminar el cliente. Verifique el log para más detalles.";
            throw BusinessLogicPGVLException.crear(userMessage, technicalMessage, exception);
        } finally {
            factory.closeConnection();
        }
		
	}

}
