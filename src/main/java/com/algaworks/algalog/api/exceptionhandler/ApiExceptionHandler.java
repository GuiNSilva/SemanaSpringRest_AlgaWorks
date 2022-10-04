package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

/*
 * Componente com proposito de tratar
 * todas excessoes dos controladores da aplicação 
  */
@AllArgsConstructor 
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo>campos = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			//String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String mensagem =messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(nome,"mensagem"));
		}
		/*
		 * personalizando a mensagem de erro com 
		 * objeto problema que foi criado para isso*/
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos, Revise o preenchimento!");
		problema.setCampos(campos);
 		return handleExceptionInternal(ex, problema, headers, status, request); 
	} 

}