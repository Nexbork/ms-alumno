package com.edu.certus.alumno.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno_curso")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoCursoEntity {
	@Id
	@Column(name = "cod_alumno")
	private Long idAlumno;
	@Column(name = "cod_curso")
	private Long idCurso;
}
