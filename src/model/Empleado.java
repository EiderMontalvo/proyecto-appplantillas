package model;

public class Empleado {
	int id;
	String Nombres, ApellidoPaterno, ApellidoMaterno;
	String Dni, Password;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombres() {
		return Nombres;
	}
	
	public void setNombres(String nombres) {
		Nombres = nombres;
	}
	
	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}
	
	public void setApellidoPaterno(String apellidoPaterno) {
		ApellidoPaterno = apellidoPaterno;
	}
	
	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}
	
	public void setApellidoMaterno(String apellidoMaterno) {
		ApellidoMaterno = apellidoMaterno;
	}
	
	public String getDni() {
		return Dni;
	}
	
	public void setDni(String dni) {
		Dni = dni;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public void setRegistro (String[] aRegistro) {
		if ( aRegistro == null) return;
		id = Integer.parseInt(aRegistro[0]);
		Nombres = aRegistro[1];
		ApellidoPaterno = aRegistro[2];
		ApellidoMaterno = aRegistro[3];
		Dni = aRegistro[4];
		Password = aRegistro[5];
	}
}
