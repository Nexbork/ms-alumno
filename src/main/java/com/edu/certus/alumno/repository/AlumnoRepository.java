package com.edu.certus.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.certus.alumno.entity.AlumnoEntity;


public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long>{

}
