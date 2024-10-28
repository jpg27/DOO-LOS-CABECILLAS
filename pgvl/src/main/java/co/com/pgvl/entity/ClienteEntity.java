package co.com.pgvl.entity;

import java.util.UUID;

import co.com.pgvl.crosscutting.helpers.TextHelper;
import co.com.pgvl.crosscutting.helpers.UUIDHelper;

public class ClienteEntity extends DomainEntity{
	
	private String nombre;
	private String numeroDocumento;
	private String numeroLicencia;
	private String tipoDocumento;
	private String correo;
	private String celular;
	private String direccion;
	
	public ClienteEntity() {
		super(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
		setNumeroDocumento(TextHelper.EMPTY);
		setNumeroLicencia(TextHelper.EMPTY);
		setTipoDocumento(TextHelper.EMPTY);
		setCorreo(TextHelper.EMPTY);
		setCelular(TextHelper.EMPTY);
		setDireccion(TextHelper.EMPTY);
	}
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}



	public String getNumeroDocumento() {
		return numeroDocumento;
	}



	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
	}



	public String getNumeroLicencia() {
		return numeroLicencia;
	}



	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = TextHelper.applyTrim(numeroLicencia);
	}



	public String getTipoDocumento() {
		return tipoDocumento;
	}



	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = TextHelper.applyTrim(correo);
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = TextHelper.applyTrim(celular);
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = TextHelper.applyTrim(direccion);
	}



	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	
	@Override
	public UUID getId() {
		return super.getId();
	}
	
}
