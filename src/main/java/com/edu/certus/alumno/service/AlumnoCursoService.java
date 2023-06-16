package com.edu.certus.alumno.service;


import com.edu.certus.alumno.dto.AlumnoCursoDto;
import com.edu.certus.alumno.dto.ResponseDto;

public interface AlumnoCursoService {

	public ResponseDto getAllAlumnoCurso();
	public ResponseDto getAlumnoCurso(Long id);
	public ResponseDto createAlumnoCurso(AlumnoCursoDto curso);
}
