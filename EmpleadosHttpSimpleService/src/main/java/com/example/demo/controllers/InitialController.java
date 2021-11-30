package com.example.demo.controllers;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.controllers.exceptions.DataNotFoundException;
import com.example.demo.controllers.exceptions.EmployeeNotFoundException;
import com.example.demo.entity.Empleado;
import com.example.demo.entity.JobEnum;
import com.example.demo.repository.BaseDatosInicial;
import com.example.demo.service.EmpleadosRepService;
import com.example.demo.service.IEmpleadosRep;

@Component
@RequestMapping("/empleados")
@WebFilter("/empleados/*")
@RestController

public class InitialController implements Filter  {

@Autowired
IEmpleadosRep empleadosRep;	

//Filtro para añadir headers a todas las respuestas
@Override
public void init(FilterConfig filterConfig) throws ServletException {}

@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader("IT-Academy-Exercise", "Simple Service");
    chain.doFilter(request, response);	
}

@Override
public void destroy() {  
} 


//Añadir objetos empleado a la base de datos para pruebas
@GetMapping("/fill")
public String fillDataBase() {
	BaseDatosInicial baseDatos = new BaseDatosInicial();
	baseDatos.iniciar();
	ArrayList<Empleado> empleados = baseDatos.getBaseDatos();
	empleadosRep.saveDateBase(empleados);
	return "Volcado de base de datos realizado con éxito";
}

//Crear empleado por parámetros.
@PostMapping("/create/{name}/{trabajo}")
public ResponseEntity<String> createEmployee(@PathVariable(name = "name") String name, @PathVariable(name = "trabajo") String trabajo) {

	 Empleado empleado = empleadosRep.saveEmployee(name, trabajo);
	   return ResponseEntity.ok("Empleado añadido con éxito: " + empleado);
}

//read
@GetMapping("/read")
public ResponseEntity<List<Empleado>> getEmpleado() {

	if (empleadosRep.listEmployees().size() > 0) {
	    return ResponseEntity.ok(empleadosRep.listEmployees());	
	   
	} else {
	    throw new DataNotFoundException("No hay empleados en la base de datos. Haz un fill.");
	}
}

//Get adicional para filtrar por empleo.
@GetMapping("/readFiltro/{empleo}")
public ResponseEntity<List<Empleado>> getEmpleadosFiltered(@PathVariable(name = "empleo") JobEnum empleo) {
	
	List<Empleado> filteredList = empleadosRep.listEmployees().stream()
	                              .filter(e -> e.getEmpleo().equalsIgnoreCase(empleo.getJobName()))
	                              .collect(Collectors.toList());
	
	if(filteredList.size() > 0) { 
	   return ResponseEntity.ok(filteredList);
	   
	} else {
		throw new DataNotFoundException("No hay empleados con la profesión indicada " + "(" + empleo + ")");
	}
	
}

//Get foto subida.
@GetMapping("/downloadFoto/{id}")
public ResponseEntity<Resource> getEmployeePhoto(@PathVariable(name = "id") int id) {
Empleado empleado1 = null;	

Optional<Empleado> empleado = empleadosRep.findEmpleadoByID(id);
		 
  if (empleado.isPresent()) {
	  empleado1 = empleado.get();
	  byte[] fotoByte = empleado1.getFoto();
	    
	    InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fotoByte));
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Disposition", String.format("attachment; filename=Empleado"));    
	    return ResponseEntity.ok()
				             .headers(headers)
				             .contentLength(fotoByte.length)
				             .contentType(MediaType.IMAGE_JPEG)
				             .body(resource);		  
  } else {
	 throw new EmployeeNotFoundException("No se ha encontrado el empleado con id: " + id);
  }
     
   
}

//Update empleado para subir foto
@PutMapping("/updateFoto/{id}")
public ResponseEntity<HttpStatus> updateEmployeePhoto(@PathVariable(name = "id") int id) {
Empleado empleado1 = null;

     try {	  
	      
    	 Optional<Empleado> empleado = empleadosRep.findEmpleadoByID(id);
		 
	       if (empleado.isPresent()) {
				  empleado1 = empleado.get();
		   }
		   
		  empleadosRep.addFoto(empleado1);
		  return new ResponseEntity<>(HttpStatus.CREATED);
			  
      } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      } 
	  
} 


//Update empleado
@PutMapping("/update/{id}/{name}/{trabajo}")
public ResponseEntity<HttpStatus> updateEmployee(@PathVariable(name = "id") int id, 
@PathVariable(name = "name") String name, @PathVariable(name = "trabajo") String trabajo) {
Empleado empleado1 = null;
//try and catch
      try {
	   
      Optional<Empleado> empleado = empleadosRep.findEmpleadoByID(id);
	 
		  if (empleado.isPresent()) {
			  empleado1 = empleado.get();
		  }
	      
		  empleadosRep.updateEmployee(empleado1, name, trabajo);   
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  
      } catch (Exception e) {
	   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   
      }   
	  
} 

//
@DeleteMapping("/delete/{id}")
public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(name = "id") int id) {

	Optional<Empleado> empleadoElim = empleadosRep.findEmpleadoByID(id);
	
	try {
		empleadosRep.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}		
}


}
