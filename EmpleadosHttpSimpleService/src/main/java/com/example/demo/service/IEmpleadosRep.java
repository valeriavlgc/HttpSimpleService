package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Empleado;

public interface IEmpleadosRep {

Optional<Empleado> findEmpleadoByID(int id);
	
List<Empleado> listEmployees(); // READ (Listar All)

Empleado saveEmployee(String name, String trabajo); // CREATE

Empleado updateEmployee(Empleado employee, String name, String trabajo); //UPDATE

void deleteEmployee(int id);// DELETE

void saveDateBase(ArrayList<Empleado> empleados);
 
void addFoto (Empleado empleado); //añadir una foto a un empleado 

	
}
