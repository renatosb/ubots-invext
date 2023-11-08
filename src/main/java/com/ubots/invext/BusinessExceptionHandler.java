package com.ubots.invext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ubots.invext.dto.BusinessExceptionDTO;

@RestControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BusinessExceptionDTO> catchDuplicidade(Exception e) {
		return new ResponseEntity<>(
				(new BusinessExceptionDTO(e.getMessage(), 
				500)), HttpStatus.valueOf(500));
	}
}
