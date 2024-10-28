package co.com.pgvl.businesslogic.adapter.entity;

import co.com.pgvl.businesslogic.adapter.Adapter;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;
import co.com.pgvl.domain.ClienteDomain;
import co.com.pgvl.entity.ClienteEntity;

public class ClienteEntityAdapter implements Adapter<ClienteDomain, ClienteEntity> {
	
	private static final Adapter<ClienteDomain, ClienteEntity> instance = new ClienteEntityAdapter();
	
	private ClienteEntityAdapter() {
		
	}
	
	public static Adapter<ClienteDomain, ClienteEntity> getCountryEntityAdapter(){
		return instance;
	}

	@Override
	public ClienteDomain adaptSource(final ClienteEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new ClienteEntity());
		return ClienteDomain.create(entityToAdapt.getId(), entityToAdapt.getNombre(), entityToAdapt.getNumeroDocumento(), entityToAdapt.getNumeroLicencia(), entityToAdapt.getTipoDocumento(), entityToAdapt.getCorreo(), entityToAdapt.getCelular(), entityToAdapt.getDireccion());

	}

	@Override
	public ClienteEntity adaptTarget(final ClienteDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, ClienteDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY));
		var entityAdapted = new ClienteEntity();
		entityAdapted.setId(domainToAdapt.getId());
		entityAdapted.setNombre(domainToAdapt.getNombre());
		entityAdapted.setNumeroDocumento(domainToAdapt.getNumeroDocumento());
		entityAdapted.setNumeroLicencia(domainToAdapt.getNumeroLicencia());
		entityAdapted.setTipoDocumento(domainToAdapt.getTipoDocumento());
		entityAdapted.setCorreo(domainToAdapt.getCorreo());
		entityAdapted.setCelular(domainToAdapt.getCelular());
		entityAdapted.setDireccion(domainToAdapt.getDireccion());
		
		return entityAdapted;
	}

}
