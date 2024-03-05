package exam.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * <p>
 * The Data transfer object representation of the To do item in Controller and
 * Service created to protect the information from the To do  Item Entity class
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
public class TodoItem {

	private Long todoId;

	@NotNull
	@NotEmpty
	private String name;

	private int status = TodoStatus.CREATED.getValue();

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
