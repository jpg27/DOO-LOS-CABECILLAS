package co.com.pgvl.businesslogic.usecase.cliente.impl;

import java.util.UUID;

import co.com.pgvl.businesslogic.adapter.entity.ClienteEntityAdapter;
import co.com.pgvl.businesslogic.usecase.cliente.RegisterNewCliente;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.domain.ClienteDomain;

public class RegisterNewClienteImpl implements RegisterNewCliente {
	
	private DAOFactory daoFactory;
	public RegisterNewClienteImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}

	@Override
	public void execute(ClienteDomain data) {
		
		var clienteDomainToMap = ClienteDomain.create(generateId(), data.getNombre(), data.getNumeroDocumento(), data.getNumeroLicencia(), data.getTipoDocumento(), data.getCorreo(), data.getCelular(), data.getDireccion());
		var clienteEntity = ClienteEntityAdapter.getClienteEntityAdapter().adaptTarget(clienteDomainToMap);
		daoFactory.getClienteDAO().create(clienteEntity);
		
	}
	
	private UUID generateId() {
		var id = UUIDHelper.generate();
		var clienteEntity = daoFactory.getClienteDAO().findById(id);
		
		if(UUIDHelper.isEqual(clienteEntity.getId(), id)){
			id = generateId();
		}
		return id;
	}
	
	private void setDaoFactory(final DAOFactory daoFactory) {
		if(ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo el registro de la informacion del cliente deseada, por favor intente de nuevo y si el problema persiste llame a Luz Mery Rios";
			var technicalMessage = "El dao factory requerido para crear la clase que actualiza el cliente llego nula";
			throw BusinessLogicPGVLException.crear(userMessage, technicalMessage);
		}
		
		this.daoFactory = daoFactory;
		// TODO Auto-generated method s	
	}

}
