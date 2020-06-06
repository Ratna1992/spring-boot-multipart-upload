package com.boot.file.upload.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import com.boot.file.upload.to.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = AccountTransactionsUploadException.class)
	public ResponseEntity<ErrorResponse> handleAccountTransactionsUploadException(
			AccountTransactionsUploadException exception) {
		return new ResponseEntity<ErrorResponse>(getErrorResponse(exception), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handle(Exception exception) {
		if (exception instanceof MaxUploadSizeExceededException) {
			return new ResponseEntity<ErrorResponse>(getErrorResponse("File/Request size is more than specified size"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ErrorResponse>(getErrorResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse getErrorResponse(Throwable exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		return errorResponse;
	}

	private ErrorResponse getErrorResponse(String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(message);
		errorResponse.setTimeStamp(LocalDateTime.now());
		return errorResponse;
	}

}
