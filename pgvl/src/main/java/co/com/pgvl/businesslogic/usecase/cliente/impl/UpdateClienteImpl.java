package co.com.pgvl.businesslogic.usecase.cliente.impl;

import co.com.pgvl.businesslogic.adapter.entity.ClienteEntityAdapter;
import co.com.pgvl.businesslogic.usecase.cliente.UpdateCliente;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.domain.ClienteDomain;

public class UpdateClienteImpl implements UpdateCliente {
	
	private DAOFactory daoFactory;
	public UpdateClienteImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}

	@Override
	public void execute(ClienteDomain data) {
		//validar politicas
		
		
		var clienteEntity = ClienteEntityAdapter.getClienteEntityAdapter().adaptTarget(data);
		daoFactory.getClienteDAO().update(clienteEntity);
		
	}

	private void setDaoFactory(final DAOFactory daoFactory) {
		if(ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la modificacion de la informacion del cliente deseado, por favor ontente de nuevo y si el problema persiste llame a Luz Mery Rios";
			var technicalMessage = "El dao factory requerido para crear la clase que actualiza al cliente llego nula";
			throw BusinessLogicPGVLException.crear(userMessage, technicalMessage);
		}
		
		this.daoFactory = daoFactory;
	}

}
