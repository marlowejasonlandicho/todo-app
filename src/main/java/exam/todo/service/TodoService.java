package exam.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.todo.dto.TodoItem;
import exam.todo.dto.TodoStatus;
import exam.todo.entity.Todo;
import exam.todo.exception.TodoItemNotFoundException;
import exam.todo.repository.TodoRepository;

/**
 * 
 * <p>
 * Service representing the Business logic for the To Do App
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public TodoItem saveTodo(TodoItem todoItem) {
		Todo todo = new Todo();
		if (todoItem.getName() != null) {
			todo.setName(todoItem.getName());
			todo.setStatus(TodoStatus.from(todoItem.getStatus()));

			Todo savedTodoItem = this.todoRepository.save(todo);

			if (savedTodoItem != null) {
				TodoItem createdTodoItem = new TodoItem();
				createdTodoItem.setTodoId(savedTodoItem.getTodoId());
				createdTodoItem.setName(savedTodoItem.getName());
				createdTodoItem.setStatus(savedTodoItem.getStatus().getValue());
				return createdTodoItem;
			}
		}
		return null;
	}

	public TodoItem getToDo(Long todoId) throws TodoItemNotFoundException {
		Optional<Todo> todoOptional = todoRepository.findById(todoId);
		Todo todo = todoOptional.orElse(null);
		if (todo != null) {
			TodoItem todoItem = new TodoItem();
			todoItem.setTodoId(todo.getTodoId());
			todoItem.setName(todo.getName());
			todoItem.setStatus(todo.getStatus().getValue());
			return todoItem;
		} else {
			throw new TodoItemNotFoundException();
		}
	}

	public List<TodoItem> getAllToDo() {
		Iterable<Todo> todoIterator = todoRepository.findAll();
		List<TodoItem> todoList = new ArrayList<>();
		todoIterator.forEach(todo -> {
			TodoItem todoItem = new TodoItem();
			todoItem.setTodoId(todo.getTodoId());
			todoItem.setName(todo.getName());
			todoItem.setStatus(todo.getStatus().getValue());
			todoList.add(todoItem);
		});

		return todoList;
	}

	public TodoItem updateTodo(Long todoId, String name, TodoStatus todoStatus) {
		Optional<Todo> todoOptional = todoRepository.findById(todoId);
		Todo todo = todoOptional.orElse(null);
		if (todo != null) {
			if (name != null) {
				todo.setName(name);
			}
			if (todoStatus != null) {
				todo.setStatus(todoStatus);
			}

			Todo savedTodo = this.todoRepository.save(todo);
			if (savedTodo != null) {
				TodoItem createdTodoItem = new TodoItem();
				createdTodoItem.setTodoId(savedTodo.getTodoId());
				createdTodoItem.setName(savedTodo.getName());
				createdTodoItem.setStatus(savedTodo.getStatus().getValue());
				return createdTodoItem;
			}
		}

		return null;
	}

	public TodoItem completeTodo(Long todoId) {
		Optional<Todo> todoOptional = todoRepository.findById(todoId);
		Todo todo = todoOptional.orElse(null);
		if (todo != null) {
			todo.setStatus(TodoStatus.COMPLETED);
			Todo savedTodo = this.todoRepository.save(todo);
			if (savedTodo != null) {
				TodoItem createdTodoItem = new TodoItem();
				createdTodoItem.setTodoId(savedTodo.getTodoId());
				createdTodoItem.setName(savedTodo.getName());
				createdTodoItem.setStatus(savedTodo.getStatus().getValue());
				return createdTodoItem;
			}
		}
		
		return null;
	}

	public boolean deleteTodo(Long todoId) {
		Optional<Todo> todoOptional = todoRepository.findById(todoId);
		Todo todo = todoOptional.orElse(null);
		if (todo != null) {
			this.todoRepository.deleteById(todoId);
			return true;
		}

		return false;
	}
}
