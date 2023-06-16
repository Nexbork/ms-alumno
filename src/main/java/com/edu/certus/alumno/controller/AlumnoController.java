package com.edu.certus.alumno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.alumno.dto.AlumnoDto;
import com.edu.certus.alumno.dto.ResponseDto;
import com.edu.certus.alumno.service.AlumnoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	//API: Interfaz de Programación de Aplicaciones
	@ApiOperation(value = "Método para listar todos alumnos")
	@GetMapping
	public ResponseEntity<ResponseDto> readAllAlumno(){
		return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAllAlummno());
	}
	
	@ApiOperation(value = "Método para obtener alumno por su id")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> readAlumno(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumno(id));
	}
	
	@ApiOperation(value = "Método para crear alumno")
	@PostMapping
	public ResponseEntity<ResponseDto> createAlumno(@RequestBody AlumnoDto alumno){
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.createAlumno(alumno));
	}
	
	@ApiOperation(value = "Método para actualizar alumno")
	@PutMapping
	public ResponseEntity<ResponseDto> updateAlumno(@RequestBody AlumnoDto alumno){
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.updateAlumno(alumno));
	}
	
	@ApiOperation(value = "Método para eliminar alumno por su id")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDto> deleteAlumno(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(alumnoService.deleteAlumno(id));
	}
	
	@ApiOperation(value = "Método para obtener todos los cursos alumnos")
	@GetMapping("/all-curso")
	public ResponseEntity<ResponseDto> getAllCursoAlumno(){
		return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAllCursoAlumno());
	}
}
