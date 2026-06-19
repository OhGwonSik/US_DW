package com.logistics.configuration;

import java.net.BindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.DuplicateKeyAutowiredException;
import com.logistics.configuration.error.ErrorResponse;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;

import lombok.extern.slf4j.Slf4j;

/*
 * 클래스명 : GlobalExceptionHandler
 * 최초생성자 : WM
 * 생성일자 : 2023.05
 * 설명 : Controller단 AOP / Exception 발생시에 Exception에 따라 핸들링하여 객체를 반환시켜준다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	MessageSource msg;

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorResponse> handleException(Exception ex, Model model) {
		log.info("::::::::::::: GlobalExceptionHandler ::::::::::::::::");
		log.error("handleException: {}", ex.getMessage());
		log.info("::::::::::::: GlobalExceptionHandler ::::::::::::::::");
		// ErrorResponse response = new ErrorResponse(999, ex.getMessage());
		// return new ResponseEntity<>(response, HttpStatus.OK); //에러로 떨어지지 않는 부분

		return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
	}

	/* common error */
	@ExceptionHandler(value = { BindException.class })
	public ResponseEntity<ErrorResponse> errorHandler(BindException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	public ResponseEntity<ErrorResponse> errorHandler(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
	}

	@ExceptionHandler(value = { NullPointerException.class })
	public ResponseEntity<ErrorResponse> errorHandler(NullPointerException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
	}

	@ExceptionHandler(value = { DuplicateKeyException.class })
	public ResponseEntity<ErrorResponse> errorHandler(DuplicateKeyException e) {
		DuplicateKeyAutowiredException Dup = new DuplicateKeyAutowiredException();
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(999, msg.getMessage(Dup.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = { InsertCheckedException.class })
	public ResponseEntity<ErrorResponse> errorHandler(InsertCheckedException e) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = { NoSaveDataException.class })
	public ResponseEntity<ErrorResponse> errorHandler(NoSaveDataException e) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = { UpdateCheckedException.class })
	public ResponseEntity<ErrorResponse> errorHandler(UpdateCheckedException e) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = { DeleteCheckedException.class })
	public ResponseEntity<ErrorResponse> errorHandler(DeleteCheckedException e) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	@ExceptionHandler(value = { ProcedureCheckedException.class })
	public ResponseEntity<ErrorResponse> errorHandler(ProcedureCheckedException e) {
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	}

	/* Http error */
	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<ErrorResponse> errorHandler(HttpClientErrorException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(404, e.getMessage()));
	}

	@ExceptionHandler(value = { HttpServerErrorException.class })
	public ResponseEntity<ErrorResponse> errorHandler(HttpServerErrorException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(500, e.getMessage()));
	}

	@ExceptionHandler(value = { UnknownHttpStatusCodeException.class })
	public ResponseEntity<ErrorResponse> errorHandler(UnknownHttpStatusCodeException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
	}
}
