package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
import javax.transaction.Transactional;

//Sobreescribir Hashcode y equals to? (https://www.youtube.com/watch?v=5G9cCJ2Wtsg, min 640)

@Entity
@Table(name = "Empleados")
public class Empleado implements Serializable {
		
@Id
@Column(name = "id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int ID;
@Column(name = "Nombre")
//@NotEmpty(message = "\"Nombre\" with a String value is required")
private String name;
@Column(name = "Empleo")
//@NotEmpty(message = "\"Empleo\" with a String value is required")
private String empleo;
@Column(name = "Salario")
private double salary;
@Lob
@Column(name = "Imagen", columnDefinition = "BLOB")
private byte[] foto;
@Transient
private JobEnum jobEnum;

public Empleado(){}


public Empleado (String name, JobEnum jobEnum) {

this.ID = ID;
this.name = name;
this.salary = jobEnum.getSalary();
this.jobEnum = jobEnum;
this.empleo = jobEnum.getJobName();
this.foto = null;
 

	
}

public int getID() {
	return ID;
}

public String getName() {
	return name;
}

public String getEmpleo() {
	return empleo;
}

public void setID(int iD) {
	ID = iD;
}

public void setName(String name) {
	this.name = name;
}

public void setJobEnum(JobEnum jobEnum) {
	this.jobEnum = jobEnum;
}

public void setFoto(byte[] foto) {
	this.foto = foto;
}

public byte[] getFoto() {
	return foto;
}


@Override
public String toString() {
	
    return "Empleado {" + "id=" + ID + ", nombre='" + name + ", empleo=" + empleo + '}';
}


}
