package com.edu.certus.alumno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto {
	private Long id;
	private String nombres;
	private String apellidos;
	private String sexo;
	private Boolean estado;
}
