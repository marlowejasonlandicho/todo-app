package exam.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.todo.dto.ApiResponse;
import exam.todo.dto.ApiStatus;
import exam.todo.dto.TodoItem;
import exam.todo.dto.TodoStatus;
import exam.todo.exception.TodoItemNotFoundException;
import exam.todo.service.TodoService;

/**
 * 
 * <p>
 * REST API for To do App
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
@RestController
@RequestMapping(path = { "/todo" }, produces = { "application/json" })
public class TodoController {

	@Autowired
	private TodoService todoService;

	@Value("${todo.created}")
	private String createdMessage;

	@Value("${todo.create_failed}")
	private String createdFailedMessage;

	@Value("${todo.found}")
	private String todoFoundMessage;

	@Value("${todo.not_found}")
	private String todoNotFoundMessage;

	@Value("${todo.updated}")
	private String todoUpdatedMessage;

	@Value("${todo.update_failed}")
	private String todoUpdateFailedMessage;

	@Value("${todo.deleted}")
	private String tododeletedMessage;

	@Value("${todo.delete_failed}")
	private String tododeleteFailedMessage;

	@Value("${todo.completed}")
	private String todoCompletedMessage;

	@Value("${todo.complete_failed}")
	private String todoCompleteteFailedMessage;

	/**
	 *
	 * <p>
	 * Create a To do item based on the To do request body
	 * </p>
	 *
	 * @param todoItem contains the todo detail
	 * 
	 * @return the created To do item in database
	 * 
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<TodoItem>> createTodo(@RequestBody TodoItem todoItem) {
		TodoItem createdTodoItem = this.todoService.saveTodo(todoItem);
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();

		if (createdTodoItem != null) {
			apiResponse.setData(createdTodoItem);
			apiResponse.setApiStatus(ApiStatus.CREATED);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(this.createdMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
		} else {
			apiResponse.setApiStatus(ApiStatus.CREATE_FAILED);
			apiResponse.setSuccess(false);
			apiResponse.setMessage(this.createdFailedMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 * <p>
	 * Retrieve a To do item based on the To do item Id
	 * </p>
	 *
	 * @param todoId the To do Id from database
	 * 
	 * @return the To do item in database
	 * 
	 */
	@GetMapping("/{todoId}")
	public ResponseEntity<ApiResponse<TodoItem>> getTodo(@PathVariable Long todoId) throws TodoItemNotFoundException {
		TodoItem todoItem = this.todoService.getToDo(todoId);
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();

		if (todoItem != null) {
			apiResponse.setData(todoItem);
			apiResponse.setApiStatus(ApiStatus.FOUND);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(this.todoFoundMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else {

			apiResponse.setApiStatus(ApiStatus.NOT_FOUND);
			apiResponse.setSuccess(false);
			apiResponse.setMessage(this.todoNotFoundMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 *
	 * <p>
	 * Retrieves all To do items
	 * </p>
	 *
	 * 
	 * @return all To do items in database
	 * 
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<TodoItem>>> getAllTodo() {
		List<TodoItem> todoItems = this.todoService.getAllToDo();
		ApiResponse<List<TodoItem>> apiResponse = new ApiResponse<>();
		if (!todoItems.isEmpty()) {
			apiResponse.setData(todoItems);
			apiResponse.setApiStatus(ApiStatus.FOUND);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(this.todoFoundMessage);
			return new ResponseEntity<ApiResponse<List<TodoItem>>>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setApiStatus(ApiStatus.NOT_FOUND);
			apiResponse.setSuccess(false);
			apiResponse.setMessage(this.todoNotFoundMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 *
	 * <p>
	 * Update a To do item based on the To do item Id and a request body
	 * </p>
	 *
	 * @param todoId  the To do Id from database
	 * @param todoItem the To do item containing data to be updated in database
	 * 
	 * @return the updated To do item in database
	 * 
	 */
	@PutMapping("/{todoId}")
	public ResponseEntity<ApiResponse<TodoItem>> updateTodo(@PathVariable Long todoId, @RequestBody TodoItem todoItem) {
		TodoItem updatedTodoItem = this.todoService.updateTodo(todoId, todoItem.getName(),
				TodoStatus.from(todoItem.getStatus()));
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();

		if (updatedTodoItem != null) {
			apiResponse.setData(updatedTodoItem);
			apiResponse.setApiStatus(ApiStatus.UPDATED);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(this.todoUpdatedMessage);
			return new ResponseEntity<ApiResponse<TodoItem>>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setApiStatus(ApiStatus.UPDATE_FAILED);
			apiResponse.setSuccess(false);
			apiResponse.setMessage(this.todoUpdateFailedMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 * <p>
	 * Completes a To do item based on the To do item Id. Sets the status to 2 -
	 * completed
	 * </p>
	 *
	 * @param todoId the To do Id from database
	 * 
	 * @return the updated To do item in database
	 * 
	 */
	@PutMapping("/complete/{todoId}")
	public ResponseEntity<ApiResponse<TodoItem>> completeTodo(@PathVariable Long todoId) {
		TodoItem updatedTodoItem = this.todoService.completeTodo(todoId);
		ApiResponse<TodoItem> apiResponse = new ApiResponse<>();

		if (updatedTodoItem != null) {
			apiResponse.setData(updatedTodoItem);
			apiResponse.setApiStatus(ApiStatus.COMPLETED);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(this.todoCompletedMessage);
			return new ResponseEntity<ApiResponse<TodoItem>>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setApiStatus(ApiStatus.COMPLETE_FAILED);
			apiResponse.setSuccess(false);
			apiResponse.setMessage(this.todoCompleteteFailedMessage);
			return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 *
	 * <p>
	 * Deletes a To do item based on the To do item Id.
	 * </p>
	 *
	 * @param todoId the To do Id from database
	 * 
	 * @return a boolean value indicating a successful deletion of the To do item in
	 *         database
	 * 
	 */
	@DeleteMapping("/{todoId}")
	public ResponseEntity<ApiResponse> deleteTodo(@PathVariable Long todoId) {
		boolean deletedTodoItem = this.todoService.deleteTodo(todoId);
		ApiResponse apiResponse = new ApiResponse<>();

		if (deletedTodoItem) {
			apiResponse.setData(deletedTodoItem);
			apiResponse.setApiStatus(ApiStatus.DELETED);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(this.tododeletedMessage);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setApiStatus(ApiStatus.DELETE_FAILED);
			apiResponse.setSuccess(false);
			apiResponse.setMessage(this.tododeleteFailedMessage);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

}
