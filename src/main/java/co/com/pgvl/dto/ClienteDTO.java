package co.com.pgvl.dto;

import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;

public class ClienteDTO extends DomainDTO{
	
	private String nombre;
	private String numeroDocumento;
	private String numeroLicencia;
	private String tipoDocumento;
	private String correo;
	private String celular;
	private String direccion;
	
	
	
	public ClienteDTO() {
		super(UUIDHelper.getDefaultAsString());
		setNombre(TextHelper.EMPTY);
		setNumeroDocumento(TextHelper.EMPTY);
		setNumeroLicencia(TextHelper.EMPTY);
		setTipoDocumento(TextHelper.EMPTY);
		setCorreo(TextHelper.EMPTY);
		setCelular(TextHelper.EMPTY);
		setDireccion(TextHelper.EMPTY);
	}
	
	public static final ClienteDTO create() {
		return new ClienteDTO();
	}


	
	public String getNombre() {
		return nombre;
	}

	public ClienteDTO setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public ClienteDTO setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
		return this;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public ClienteDTO setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = TextHelper.applyTrim(numeroLicencia);
		return this;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public ClienteDTO setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
		return this;
	}

	public String getCorreo() {
		return correo;
	}

	public ClienteDTO setCorreo(String correo) {
		this.correo = TextHelper.applyTrim(correo);
		return this;
	}

	public String getCelular() {
		return celular;
	}

	public ClienteDTO setCelular(String celular) {
		this.celular = TextHelper.applyTrim(celular);
		return this;
	}

	public String getDireccion() {
		return direccion;
	}

	public ClienteDTO setDireccion(String direccion) {
		this.direccion = TextHelper.applyTrim(direccion);
		return this;
	}

	public ClienteDTO setId(final String id) {
		super.setIdentifier(id);
		return this;
	}
	
	@Override
	public String getId() {
		return super.getId();
	
	}

}
