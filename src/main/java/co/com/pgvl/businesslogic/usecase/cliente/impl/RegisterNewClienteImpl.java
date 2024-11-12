package co.com.pgvl.businesslogic.usecase.cliente.impl;

import java.util.UUID;

import co.com.pgvl.businesslogic.adapter.entity.ClienteEntityAdapter;
import co.com.pgvl.businesslogic.usecase.cliente.RegisterNewCliente;
import co.com.pgvl.businesslogic.usecase.cliente.rules.NombreClienteConsistencyIsValid;
import co.com.pgvl.businesslogic.usecase.cliente.rules.ClienteDoesNotExist;
import co.com.pgvl.businesslogic.usecase.cliente.rules.CorreoClienteConsistencyIsValid;
import co.com.pgvl.businesslogic.usecase.cliente.rules.CorreoClienteIsUnique;
import co.com.pgvl.businesslogic.usecase.cliente.rules.DireccionClienteConsistencyIsValid;
import co.com.pgvl.businesslogic.usecase.cliente.rules.NumeroCelularClienteConsistencyIsValid;
import co.com.pgvl.businesslogic.usecase.cliente.rules.NumeroDocumentoClienteConsistencyIsValid;
import co.com.pgvl.businesslogic.usecase.cliente.rules.TipoDocumentoClienteConsistencyIsValid;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.NombreClienteConsistencyIsValidImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.ClienteDoesNotExistImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.CorreoClienteConsistencyIsValidImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.CorreoClienteIsUniqueImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.DireccionClienteConsistencyIsValidImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.NumeroCelularClienteConsistencyIsValidImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.NumeroDocumentoClienteConsistencyIsValidImpl;
import co.com.pgvl.businesslogic.usecase.cliente.rules.impl.TipoDocumentoClienteConsistencyIsValidImpl;
import co.com.pgvl.crosscutting.exceptions.BusinessLogicPGVLException;
import co.com.pgvl.crosscutting.helpers.ObjectHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;
import co.com.pgvl.data.dao.DAOFactory;
import co.com.pgvl.domain.ClienteDomain;

public class RegisterNewClienteImpl implements RegisterNewCliente {
	
	private DAOFactory daoFactory;
	private NombreClienteConsistencyIsValid nombreClienteConsistencyIsValid = new NombreClienteConsistencyIsValidImpl();
	private NumeroDocumentoClienteConsistencyIsValid numeroDocumentoClienteConsistencyIsValid = new NumeroDocumentoClienteConsistencyIsValidImpl();
	private TipoDocumentoClienteConsistencyIsValid tipoDocumentoClienteConsistencyIsValid = new TipoDocumentoClienteConsistencyIsValidImpl();
	private CorreoClienteConsistencyIsValid correoClienteConsistencyIsValid =  new CorreoClienteConsistencyIsValidImpl();
	private NumeroCelularClienteConsistencyIsValid numeroCelularClienteConsistencyIsValid = new NumeroCelularClienteConsistencyIsValidImpl();
	private DireccionClienteConsistencyIsValid direccionClienteConsistencyIsValid = new DireccionClienteConsistencyIsValidImpl();
	private ClienteDoesNotExist clienteDoesNotExist;
	private CorreoClienteIsUnique correoClienteIsUnique = new CorreoClienteIsUniqueImpl();
	public RegisterNewClienteImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
		
		this.clienteDoesNotExist = new ClienteDoesNotExistImpl();
	}

	@Override
	public void execute(final ClienteDomain data) {
		//validar politicas
		//validacion de datos
		nombreClienteConsistencyIsValid.execute(data.getNombre());
		numeroDocumentoClienteConsistencyIsValid.execute(data.getNumeroDocumento());
		numeroDocumentoClienteConsistencyIsValid.execute(data.getNumeroLicencia());
		tipoDocumentoClienteConsistencyIsValid.execute(data.getTipoDocumento());
		correoClienteConsistencyIsValid.execute(data.getCorreo());
		numeroCelularClienteConsistencyIsValid.execute(data.getCelular());
		direccionClienteConsistencyIsValid.execute(data.getDireccion());
		
		//si el ya existe
		clienteDoesNotExist.execute(data.getId().toString(), daoFactory);
		
		//el correo del cliente no puede ser igual al de uno ya registrado
		correoClienteIsUnique.execute(data.getCorreo(), daoFactory);
		
		
		
		
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
