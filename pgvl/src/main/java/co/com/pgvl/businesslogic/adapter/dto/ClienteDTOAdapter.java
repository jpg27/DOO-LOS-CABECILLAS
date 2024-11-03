package co.com.pgvl.businesslogic.adapter.dto;

import co.com.pgvl.businesslogic.adapter.Adapter;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;
import co.com.pgvl.domain.ClienteDomain;
import co.com.pgvl.dto.ClienteDTO;

public final class ClienteDTOAdapter implements Adapter<ClienteDomain, ClienteDTO>{
	
	private static final Adapter<ClienteDomain, ClienteDTO> instance = new ClienteDTOAdapter();
	
	private ClienteDTOAdapter() {
		
	}
	
	public static Adapter<ClienteDomain, ClienteDTO> getClienteDTOAdapter(){
		return instance;
	}

	@Override
	public ClienteDomain adaptSource(final ClienteDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data, ClienteDTO.create());
		return ClienteDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), data.getNombre(), data.getTipoDocumento(), data.getNumeroLicencia(), data.getTipoDocumento(), data.getCorreo(), data.getCelular(), data.getDireccion());
	}

	@Override
	public ClienteDTO adaptTarget(final ClienteDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, ClienteDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY));
		return ClienteDTO.create().setId("").setNombre(domainToAdapt.getNombre()).setNumeroDocumento(domainToAdapt.getNumeroDocumento()).setNumeroLicencia(domainToAdapt.getNumeroLicencia()).setTipoDocumento(domainToAdapt.getTipoDocumento()).setCorreo(domainToAdapt.getCorreo()).setCelular(domainToAdapt.getCelular()).setDireccion(domainToAdapt.getDireccion());
	}

}
