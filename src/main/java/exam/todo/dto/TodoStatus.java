package exam.todo.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>
 * Enumeration of values representing the state of the To do item: <br>
 * 0 - Created/New<br>
 * 1 - In-Progress<br>
 * 2 - Completed/Done<br>
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
public enum TodoStatus {

	CREATED(0), IN_PROGRESS(1), COMPLETED(2);

	private int status;

	private static Map<Integer, TodoStatus> _map = new HashMap<Integer, TodoStatus>();

	static {
		for (TodoStatus todoStatus : TodoStatus.values()) {
			_map.put(todoStatus.status, todoStatus);
		}
	}

	TodoStatus(int status) {
		this.status = status;
	}

	public int getValue() {
		return this.status;
	}

	public static TodoStatus from(int status) {
		return _map.get(status);
	}
}
