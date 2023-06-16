package com.edu.certus.alumno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.alumno.client.CursoClient;
import com.edu.certus.alumno.dto.AlumnoDto;
import com.edu.certus.alumno.dto.CursoResponseDto;
import com.edu.certus.alumno.dto.ResponseDto;
import com.edu.certus.alumno.entity.AlumnoEntity;
import com.edu.certus.alumno.repository.AlumnoRepository;
import com.edu.certus.alumno.service.AlumnoService;
import com.edu.certus.alumno.util.Constante;
import com.edu.certus.alumno.util.Util;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private CursoClient cursoClient;
	
	@Override
	public ResponseDto getAllAlummno() {
		try {
			List<AlumnoEntity> listAlumnoEntity = alumnoRepository.findAll();
			if(listAlumnoEntity.isEmpty()) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			List<AlumnoDto> list = new ArrayList<AlumnoDto>();
			for (AlumnoEntity alumnoEntity : listAlumnoEntity) {
				list.add(AlumnoDto.builder()
						.id(alumnoEntity.getId())
						.nombres(alumnoEntity.getNombres())
						.apellidos(alumnoEntity.getApellidos())
						.sexo(alumnoEntity.getSexo())
						.estado(alumnoEntity.getEstado())
						.build());
			}
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, list);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto getAlumno(Long id) {
		try {
			AlumnoEntity alumnoEntity = alumnoRepository.findById(id).orElse(null);
			if(null == alumnoEntity) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			AlumnoDto alumnoDto = AlumnoDto.builder()
					.id(alumnoEntity.getId())
					.nombres(alumnoEntity.getNombres())
					.apellidos(alumnoEntity.getApellidos())
					.sexo(alumnoEntity.getSexo())
					.estado(alumnoEntity.getEstado())
					.build();
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, alumnoDto);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto createAlumno(AlumnoDto alumno) {
		try {
			AlumnoEntity alumnoEntity = AlumnoEntity.builder()
					.nombres(alumno.getNombres())
					.apellidos(alumno.getApellidos())
					.sexo(alumno.getSexo())
					.estado(true)
					.build();
				alumnoRepository.save(alumnoEntity);
				alumno.setId(alumnoEntity.getId());
				return Util.getResponse(true, Constante.OPERATION_SUCCESS, alumno);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto updateAlumno(AlumnoDto alumno) {
		try {
			AlumnoEntity alumnoEntity = alumnoRepository.findById(alumno.getId()).orElse(null);
			if(null == alumnoEntity) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			alumnoEntity.setNombres(alumno.getNombres());
			alumnoEntity.setApellidos(alumno.getApellidos());
			alumnoEntity.setSexo(alumno.getSexo());
			alumnoRepository.save(alumnoEntity);
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, alumno);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto deleteAlumno(Long id) {
		try {
			AlumnoEntity alumnoEntity = alumnoRepository.findById(id).orElse(null);
			alumnoEntity.setEstado(false);
			alumnoRepository.save(alumnoEntity);
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			log.error(Constante.OPERATION_FAILED, e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto getAllCursoAlumno() {
		
		try {
			List<AlumnoEntity> listAlumnoEntity = alumnoRepository.findAll();
			ResponseDto resposeDto = cursoClient.readAllCurso();
			
			List<AlumnoDto> listAlumno = new ArrayList<AlumnoDto>();
			for (AlumnoEntity alumnoEntity : listAlumnoEntity) {
				AlumnoDto.builder()
					.id(alumnoEntity.getId())
					.nombres(alumnoEntity.getNombres())
					.apellidos(alumnoEntity.getApellidos())
					.sexo(alumnoEntity.getSexo())
					.estado(alumnoEntity.getEstado())
					.build();
					
				}
				CursoResponseDto cursoDto = CursoResponseDto.builder()
								.alumno(listAlumno)
								.curso(resposeDto.getData())
								.build();
				return Util.getResponse(true, Constante.OPERATION_SUCCESS, cursoDto);
		} catch (RetryableException ex) {
			log.error("Exception:"+ Constante.OPERATION_FAILED+" "+ex);
			return Util.getResponse(false, Constante.NO_SERVICE_AVAILABLE, null);
		} catch (Exception e) {
			log.error("Exception:"+ Constante.OPERATION_FAILED+" "+e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
			
		}
		
		
	}
}
