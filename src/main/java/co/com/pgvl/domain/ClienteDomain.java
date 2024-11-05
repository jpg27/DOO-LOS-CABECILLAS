package co.com.pgvl.domain;

import java.util.UUID;

import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;

public class ClienteDomain extends Domain{
	
	private String nombre;
	private String numeroDocumento;
	private String numeroLicencia;
	private String tipoDocumento;
	private String correo;
	private String celular;
	private String direccion;
	
	private ClienteDomain(final UUID id, final String nombre, final String numeroDocumento, final String numeroLicencia,
			final String tipoDocumento, final String correo, final String celular, final String direccion) {
		super(id);
		setNombre(nombre);
		setNumeroDocumento(numeroDocumento);
		setNumeroLicencia(numeroLicencia);
		setTipoDocumento(tipoDocumento);
		setCorreo(correo);
		setCelular(celular);
		setDireccion(direccion);
	}
	
	public static final ClienteDomain create(final UUID id, final String nombre, final String numeroDocumento, final String numeroLicencia,
			final String tipoDocumento, final String correo, final String celular, final String direccion) {
		return new ClienteDomain(id, nombre, numeroDocumento, numeroLicencia, tipoDocumento, correo, celular, direccion);
	}
	
	static final ClienteDomain create() {
		return new ClienteDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY );
	}
	
	@Override
	public UUID getId() {
		return super.getId();
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	private void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	private void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = TextHelper.applyTrim(numeroLicencia);
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	private void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
	}

	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		this.correo = TextHelper.applyTrim(correo);
	}

	public String getCelular() {
		return celular;
	}

	private void setCelular(String celular) {
		this.celular = TextHelper.applyTrim(celular);
	}

	public String getDireccion() {
		return direccion;
	}

	private void setDireccion(String direccion) {
		this.direccion = TextHelper.applyTrim(direccion);
	}

}
