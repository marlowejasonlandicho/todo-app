package exam.todo.entity;

import exam.todo.dto.TodoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * <p>
 * Entity class representing the To do item in database
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
@Entity
@Table(name = "todo")
public class Todo {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "todo_id")
	private Long todoId;

	@Column
	private String name;

	@Column
	private TodoStatus status = TodoStatus.CREATED;

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

	public TodoStatus getStatus() {
		return status;
	}

	public void setStatus(TodoStatus status) {
		this.status = status;
	}
}
