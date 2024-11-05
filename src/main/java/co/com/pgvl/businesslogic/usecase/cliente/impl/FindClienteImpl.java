package co.com.pgvl.businesslogic.usecase.cliente.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.com.pgvl.businesslogic.adapter.entity.ClienteEntityAdapter;
import co.com.pgvl.businesslogic.usecase.cliente.FindCliente;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.domain.ClienteDomain;
import co.com.pgvl.entity.ClienteEntity;

public final class FindClienteImpl implements FindCliente{
	   private final DAOFactory daoFactory;

	    public FindClienteImpl(DAOFactory daoFactory) {
	        this.daoFactory = daoFactory;
	    }

	@Override
	public List<ClienteDomain> execute(ClienteDomain data) {
        List<ClienteEntity> clienteEntities;

        if (ObjectHelper.isNull(data)) {
            clienteEntities = daoFactory.getClienteDAO().findAll();
        } else {
            ClienteEntity filterEntity = ClienteEntityAdapter.getClienteEntityAdapter().adaptTarget(data);
            clienteEntities = daoFactory.getClienteDAO().findByFilter(filterEntity);
        }
        return clienteEntities.stream().map(ClienteEntityAdapter.getClienteEntityAdapter()::adaptSource).collect(Collectors.toList());
	}



}
