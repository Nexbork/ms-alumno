package com.edu.certus.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.certus.alumno.entity.AlumnoCursoEntity;


public interface AlumnoCursoRepository extends JpaRepository<AlumnoCursoEntity, Long>{
	AlumnoCursoEntity findByIdAlumno(Long id);
}
