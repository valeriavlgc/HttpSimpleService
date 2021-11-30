package com.example.demo.repository;

import java.util.ArrayList;
import com.example.demo.entity.Empleado;
import com.example.demo.entity.JobEnum;

public class BaseDatosInicial {
	
ArrayList<Empleado> empleados = new ArrayList<Empleado>();

public void iniciar() {
	Empleado empleado = new Empleado("Pedro", JobEnum.Camarero);
	Empleado empleado1 = new Empleado("Juana", JobEnum.Camarero);
	Empleado empleado2 = new Empleado("María", JobEnum.Camarero);
	Empleado empleado3 = new Empleado("Marcos", JobEnum.Camarero);
	Empleado empleado4 = new Empleado("Jose", JobEnum.Cocinero);
	Empleado empleado5 = new Empleado("Pablo", JobEnum.Cocinero);
	Empleado empleado6 = new Empleado("Arancha", JobEnum.Cocinero);
	Empleado empleado7 = new Empleado("Roberto", JobEnum.Runner);
	Empleado empleado8 = new Empleado("Olivia", JobEnum.Runner);
	Empleado empleado9 = new Empleado("Jacobo", JobEnum.Director);
	empleados.add(empleado9); empleados.add(empleado8); empleados.add(empleado7);
	empleados.add(empleado6);empleados.add(empleado5);empleados.add(empleado4);
	empleados.add(empleado3);empleados.add(empleado2);empleados.add(empleado1);
	empleados.add(empleado);
}

public ArrayList<Empleado> getBaseDatos() {
	return empleados;
}
	
}

