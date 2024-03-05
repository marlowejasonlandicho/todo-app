package exam.todo.dto;

/**
 * 
 * <p>
 * REST API Response representation for the To do App used primarily by the
 * TodoController
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */

public class ApiResponse<T> {

	private ApiStatus apiStatus = ApiStatus.FAILED;

	private String message;

	private boolean success = false;

	private T data;

	public ApiStatus getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(ApiStatus apiStatus) {
		this.apiStatus = apiStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
