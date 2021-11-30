package com.example.demo.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Empleado;
import com.example.demo.entity.JobEnum;
import com.example.demo.repository.EmpleadosRepositorio;

@Service
public class EmpleadosRepService implements IEmpleadosRep {

@Autowired
EmpleadosRepositorio empleadosRep;
	
    public Optional<Empleado> findEmpleadoByID(int id) {
        return empleadosRep.findById(id);

    }

	@Override
	public List<Empleado> listEmployees() {
		return empleadosRep.findAll();
	}


	@Override
	public Empleado saveEmployee(String name, String trabajo) {
		Empleado empleado = null;
		
		  if (trabajo.equalsIgnoreCase("Director")) {
			 empleado = new Empleado(name, JobEnum.Director);
		  } else if (trabajo.equalsIgnoreCase("Camarero")) {
			 empleado = new Empleado(name, JobEnum.Camarero);
		  } else if (trabajo.equalsIgnoreCase("Runner")) {
			 empleado = new Empleado(name, JobEnum.Runner);
		  } else if (trabajo.equalsIgnoreCase("Cocinero")) {
			 empleado = new Empleado(name, JobEnum.Cocinero);
		  }
		  
		   return empleadosRep.save(empleado);
	}


	@Override
	public Empleado updateEmployee(Empleado employee, String name, String trabajo) {
	
		if (trabajo.equalsIgnoreCase("Director")) {
			 employee.setJobEnum(JobEnum.Director);
		  } else if (trabajo.equalsIgnoreCase("Camarero")) {
			 employee.setJobEnum(JobEnum.Camarero);
		  } else if (trabajo.equalsIgnoreCase("Runner")) {
			 employee.setJobEnum(JobEnum.Runner);
		  } else if (trabajo.equalsIgnoreCase("Cocinero")) {
			 employee.setJobEnum(JobEnum.Cocinero);
		}
	  
	   employee.setName(name);

	   //así añadiria uno nuevo. 
		return empleadosRep.save(employee);
	}


	@Override
	public void deleteEmployee(int id) {
		empleadosRep.deleteById(id);
		
	}

	@Override
	public void saveDateBase(ArrayList<Empleado> empleados) {
		 empleadosRep.saveAll(empleados);
		
	}

	
	//Pasar la imágen.
	@Override
	public void addFoto(Empleado empleado) {
	byte[] foto = null;	
		
	  InputStream inputStream = this.getClass()
								  .getClassLoader()
								  .getResourceAsStream("Empleado.jpg");
		
		try {
			foto = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		empleado.setFoto(foto);
		empleadosRep.save(empleado);
		
	}	

}
