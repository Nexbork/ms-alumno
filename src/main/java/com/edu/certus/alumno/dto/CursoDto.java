package com.edu.certus.alumno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {
	
	private Long id;
	private String descripcion;
	private Boolean estado;

}
