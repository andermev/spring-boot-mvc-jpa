package com.udea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.model.Materia;

@Repository("materiaRepository")
public interface MateriaRepository extends JpaRepository<Materia, Long>{
	List<Materia> obtenerMateriasByEstudiante(String estudianteId);
	
}
