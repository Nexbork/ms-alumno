package com.edu.certus.alumno.service;

import com.edu.certus.alumno.dto.AlumnoDto;
import com.edu.certus.alumno.dto.ResponseDto;

public interface AlumnoService {

	//CRUD: C=CREATE, R=READ, U=UPDATE, D=DELETE
	public ResponseDto getAllAlummno();
	public ResponseDto getAlumno(Long id);
	public ResponseDto createAlumno(AlumnoDto alumno);
	public ResponseDto updateAlumno(AlumnoDto alumno);
	public ResponseDto deleteAlumno(Long id);
	
	public ResponseDto getAllCursoAlumno();
}
