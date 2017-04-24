package com.udea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.model.Aula;

@Repository("aulaRepository")
public interface AulaRepository extends JpaRepository<Aula, Long>{
	List<Aula> obtenerAulasByProfesor(String profesor);
	
}
