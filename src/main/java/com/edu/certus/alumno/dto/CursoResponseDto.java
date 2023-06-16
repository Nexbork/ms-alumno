package com.edu.certus.alumno.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDto {
	
	private List<AlumnoDto> alumno;
	private Object curso;
}
