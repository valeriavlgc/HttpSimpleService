package com.example.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Empleado;


@Repository
@Transactional
public interface EmpleadosRepositorio extends JpaRepository<Empleado, Integer> {
	
	List<Empleado> findEmpleadoByID(int id);
	

}
