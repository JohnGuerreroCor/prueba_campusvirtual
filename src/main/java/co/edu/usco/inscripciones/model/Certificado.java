package co.edu.usco.inscripciones.model;

public class Certificado {
	
	private String name;
	private String nameEnc;
	private String fileExtension;
	private String fileLocation;
	private long codigoRegistro;
	public Certificado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Certificado(String name, String nameEnc, String fileExtension, String fileLocation, long codigoRegistro) {
		super();
		this.name = name;
		this.nameEnc = nameEnc;
		this.fileExtension = fileExtension;
		this.fileLocation = fileLocation;
		this.codigoRegistro = codigoRegistro;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEnc() {
		return nameEnc;
	}
	public void setNameEnc(String nameEnc) {
		this.nameEnc = nameEnc;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public long getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(long codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	
}
