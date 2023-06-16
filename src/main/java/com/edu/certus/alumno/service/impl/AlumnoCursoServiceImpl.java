package com.edu.certus.alumno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.alumno.client.CursoClient;
import com.edu.certus.alumno.dto.AlumnoCursoDto;
import com.edu.certus.alumno.dto.CursoDto;
import com.edu.certus.alumno.dto.ResponseDto;
import com.edu.certus.alumno.entity.AlumnoCursoEntity;
import com.edu.certus.alumno.entity.AlumnoEntity;
import com.edu.certus.alumno.repository.AlumnoCursoRepository;
import com.edu.certus.alumno.repository.AlumnoRepository;
import com.edu.certus.alumno.service.AlumnoCursoService;
import com.edu.certus.alumno.util.Constante;
import com.edu.certus.alumno.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlumnoCursoServiceImpl implements AlumnoCursoService {

	@Autowired
	private AlumnoCursoRepository alumnoCursoRepository;
	
	@Autowired
	private AlumnoRepository alumnoRespository; 
	
	@Autowired
	private CursoClient cursoClient;
	
	@Override
	public ResponseDto getAllAlumnoCurso() {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			List<AlumnoCursoEntity> listAlumnoCursoEntity = alumnoCursoRepository.findAll();
			
			List<AlumnoCursoDto> listAlumnoCursoDto = new ArrayList<AlumnoCursoDto>();
			
			for (int i = 0; i < listAlumnoCursoEntity.size(); i++) {
				AlumnoEntity alumnoEntity = alumnoRespository.findById(listAlumnoCursoEntity.get(i).getIdAlumno()).orElse(null);
				
				ResponseDto responseDto = cursoClient.readCurso(listAlumnoCursoEntity.get(i).getIdCurso());
				
				CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);
				
				listAlumnoCursoDto.add(AlumnoCursoDto.builder()
						.idAlumno(alumnoEntity.getId())
						.nombreAlumno(alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos())
						.estadoAlumno(alumnoEntity.getEstado())
						.idCurso(cursoDto.getId())
						.nombreCurso(cursoDto.getDescripcion())
						.build());
			}
			
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, listAlumnoCursoDto);
		}catch(RetryableException ex){
			log.error("RetrytableException:"+ Constante.NO_SERVICE_AVAILABLE +" "+ ex);
			return Util.getResponse(false, Constante.NO_SERVICE_AVAILABLE, null);
		} catch (Exception e) {
			log.error("Exception:"+ Constante.OPERATION_FAILED +" "+ e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto getAlumnoCurso(Long id) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			AlumnoCursoEntity alumnoCursoEntity = alumnoCursoRepository.findByIdAlumno(id);
			if(alumnoCursoEntity == null) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			AlumnoEntity alumnoEntity = alumnoRespository.findById(alumnoCursoEntity.getIdAlumno()).orElse(null);
			ResponseDto responseDto = cursoClient.readCurso(alumnoCursoEntity.getIdCurso());
			CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);
			
			AlumnoCursoDto alumnoCursoDto = AlumnoCursoDto.builder()
					.idAlumno(alumnoEntity.getId())
					.nombreAlumno(alumnoEntity.getNombres()+" "+ alumnoEntity.getApellidos())
					.estadoAlumno(alumnoEntity.getEstado())
					.idCurso(cursoDto.getId())
					.nombreCurso(cursoDto.getDescripcion())
					.build();
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, alumnoCursoDto);
		}catch (RetryableException ex) {
			log.error("Exception:"+Constante.OPERATION_FAILED+" "+ex);
				return Util.getResponse(false, Constante.NO_SERVICE_AVAILABLE, null);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto createAlumnoCurso(AlumnoCursoDto curso) {
		try {
			AlumnoCursoEntity alumnoCursoEntity = AlumnoCursoEntity.builder()
				.idAlumno(curso.getIdAlumno())
				.idCurso(curso.getIdCurso())
				.build();
			alumnoCursoRepository.save(alumnoCursoEntity);
			
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, null);	
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
