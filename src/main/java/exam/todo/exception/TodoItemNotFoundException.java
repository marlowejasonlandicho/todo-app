package exam.todo.exception;

public class TodoItemNotFoundException extends Exception {

	public TodoItemNotFoundException() {
		super("To Do Item not found");
	}

	public TodoItemNotFoundException(String message) {
		super(message);
	}

}
