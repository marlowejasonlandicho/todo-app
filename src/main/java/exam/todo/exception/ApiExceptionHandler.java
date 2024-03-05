package exam.todo.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import exam.todo.dto.ApiResponse;
import exam.todo.dto.ApiStatus;
import exam.todo.dto.TodoItem;
import jakarta.el.MethodNotFoundException;

/**
 * 
 * <p>
 * REST API wide exception handler for the To do App
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeException(RuntimeException rex) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(rex.getMessage());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodNotFoundException.class)
	protected ResponseEntity<Object> handleMethodNotFoundException(MethodNotFoundException mnfex) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(mnfex.getMessage());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException noResourceFoundException,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(noResourceFoundException.getMessage());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodNotAllowedException.class)
	protected ResponseEntity<ApiResponse> handleMethodNotAllowedException(MethodNotAllowedException mnaex) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(mnaex.getMessage());
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException npex) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.FAILED);
		apiResponse.setMessage(npex.getMessage());
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TodoItemNotFoundException.class)
	ResponseEntity<ApiResponse> todoItemNotFoundHandler(TodoItemNotFoundException ex) {
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();
		apiResponse.setApiStatus(ApiStatus.NOT_FOUND);
		apiResponse.setMessage(ex.getMessage());
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
