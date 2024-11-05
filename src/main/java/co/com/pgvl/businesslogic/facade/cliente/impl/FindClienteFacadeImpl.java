package co.com.pgvl.businesslogic.facade.cliente.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.com.pgvl.businesslogic.adapter.dto.ClienteDTOAdapter;
import co.com.pgvl.businesslogic.facade.cliente.FindClienteFacade;
import co.com.pgvl.businesslogic.usecase.cliente.impl.FindClienteImpl;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.exceptions.PGVLException;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.data.dao.enums.DAOSource;
import co.com.pgvl.domain.ClienteDomain;
import co.com.pgvl.dto.ClienteDTO;

public class FindClienteFacadeImpl implements FindClienteFacade {

	@Override
	public List<ClienteDTO> execute(final ClienteDTO data) {
		var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);
		
		try {
			var findClienteUseCase = new FindClienteImpl(factory);
			var clienteDomain = ClienteDTOAdapter.getClienteDTOAdapter().adaptSource(data);

			List<ClienteDomain> clienteDomainList = findClienteUseCase.execute(clienteDomain);

			List<ClienteDTO> clienteDTOList = clienteDomainList.stream().map(ClienteDTOAdapter.getClienteDTOAdapter()::adaptTarget).collect(Collectors.toList());

			return clienteDTOList;

			
		}catch(final PGVLException exception){
			throw exception;
		}catch(final Exception exception){
			var userMessage = "Se ha presentado un problema tratando de consultar la informacion del nuevo cliente";
			var technicalMessage = "se ha presentado un problema inesperado consultando la informacion del nuevo cliente por favor revise el log de errores para tener mas detalles";
			
			throw BusinessLogicPGVLException.crear(userMessage, technicalMessage, exception);
		}finally {
			factory.closeConnection();
		}
	}

}
