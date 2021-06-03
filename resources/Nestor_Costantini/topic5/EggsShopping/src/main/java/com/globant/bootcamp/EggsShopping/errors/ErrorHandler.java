package com.globant.bootcamp.EggsShopping.errors;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@RestControllerAdvice
public class ErrorHandler  {
	private final Log LOG  = LogFactory.getLog(this.getClass());

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorInfo methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
			
		BindingResult result = e.getBindingResult();
		List<FieldError>fieldErrors= result.getFieldErrors(); 
		
		StringBuilder errorMessage = new StringBuilder();
		fieldErrors.forEach(f -> errorMessage.append(f.getField()+ " "+ f.getDefaultMessage()+" ")); 
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(),request.getRequestURI());
        
		return errorInfo;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	public ErrorInfo entityNotFoundException(HttpServletRequest request, EntityNotFoundException e){
		LOG.info("NOT FOUND ERROR");
		ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND.value(), "hola", request.getRequestURI());
		return error;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ErrorInfo httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
		ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMostSpecificCause().getMessage(), request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ErrorInfo dataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException e) {
		
		ErrorInfo error = new ErrorInfo(HttpStatus.CONFLICT.value(), e.getMostSpecificCause().getMessage(), request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseBody
	public ErrorInfo EmptyResultDataAccessException(HttpServletRequest request, EmptyResultDataAccessException e) {
		
		ErrorInfo error = new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getMostSpecificCause().getMessage(), request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public ErrorInfo numberFormatException(HttpServletRequest request, NumberFormatException e) {
		
		ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage() ,request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ErrorInfo illegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
		
		ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage() ,request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseBody
	public ErrorInfo 	constraintViolationException(HttpServletRequest request, javax.validation.ConstraintViolationException e) {
		List<String> errorString = e.getConstraintViolations().stream().map(error -> error.getMessage().toString()).collect(Collectors.toList());
		
		ErrorInfo error = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorString.toString() ,request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedUserException.class)
	@ResponseBody
	public ErrorInfo unauthorizedUserException(HttpServletRequest request, UnauthorizedUserException e) {
		String errorString = e.getCause().getMessage();
		
		ErrorInfo error = new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), errorString.toString() ,request.getRequestURI() );
		return  error;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidTokenException.class)
	@ResponseBody
	public ErrorInfo invalidTokenException(HttpServletRequest request, InvalidTokenException e) {
		String errorString = e.getCause().getMessage();
		
		ErrorInfo error = new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), errorString.toString() ,request.getRequestURI() );
		return  error;
	}
	
	
}
