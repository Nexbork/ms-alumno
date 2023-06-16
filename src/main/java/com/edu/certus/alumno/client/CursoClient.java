package com.edu.certus.alumno.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.certus.alumno.dto.ResponseDto;


@FeignClient(name="ms-curso", url="http://localhost:8080")
public interface CursoClient {
	
	@GetMapping("/v1/curso")
	ResponseDto readAllCurso();
	
	@GetMapping("/v1/curso/{id}")
	ResponseDto readCurso(@PathVariable("id") Long id);
}
