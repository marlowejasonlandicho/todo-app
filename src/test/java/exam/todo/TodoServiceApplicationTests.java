package exam.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import exam.todo.dto.TodoStatus;
import exam.todo.entity.Todo;
import exam.todo.repository.TodoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TodoServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class TodoServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TodoRepository todoRepository;

	@Test
	public void whenCreateTodo_thenStatus200() throws Exception {
		mvc.perform(post("/todo").content("{\"name\": \"New Todo\"}").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.data.name", is("New Todo")))
				.andExpect(jsonPath("$.data.status", is(0)));
	}

	@Test
	public void givenTodoItemIs1_whenGetTodo_thenStatus200() throws Exception {
		Todo savedTodo = createTestTodo("Todo Item 1", TodoStatus.CREATED);
		mvc.perform(get("/todo/{todoId}", savedTodo.getTodoId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.name", is("Todo Item 1")))
				.andExpect(jsonPath("$.data.status", is(0)));
	}

	@Test
	public void givenTodoItems_whenGetTodos_thenStatus200() throws Exception {
		Todo savedTodo = createTestTodo("Todo Item 1", TodoStatus.CREATED);
		Todo anotherSavedTodo = createTestTodo("Todo Item 2", TodoStatus.IN_PROGRESS);

		mvc.perform(get("/todo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].name", is("Todo Item 1"))).andExpect(jsonPath("$.data[0].status", is(0)))
				.andExpect(jsonPath("$.data[1].name", is("Todo Item 2")))
				.andExpect(jsonPath("$.data[1].status", is(1)));
	}

	@Test
	public void givenTodoItemIs1_whenGetTodoIsEmpty_thenStatus404() throws Exception {
		mvc.perform(get("/todo/100").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.apiStatus", is("NOT_FOUND"))).andExpect(jsonPath("$.success", is(false)));
	}

	@Test
	public void whenCompleteTodo_thenStatus200() throws Exception {
		Todo savedTodo = createTestTodo("Todo Item", TodoStatus.IN_PROGRESS);
		mvc.perform(put("/todo/complete/{todoId}", savedTodo.getTodoId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.name", is("Todo Item")))
				.andExpect(jsonPath("$.data.status", is(2)));
	}

	@Test
	public void whenCompleteTodoMissingId_thenStatus404() throws Exception {
		mvc.perform(put("/todo/complete/100").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.apiStatus", is("COMPLETE_FAILED"))).andExpect(jsonPath("$.success", is(false)));
	}

	@Test
	public void whenUpdateTodo_thenStatus200() throws Exception {
		Todo savedTodo = createTestTodo("Todo Item 1", TodoStatus.CREATED);
		mvc.perform(put("/todo/{todoId}", savedTodo.getTodoId()).content("{\"name\": \"Updated Todo\",\"status\": 2}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.name", is("Updated Todo"))).andExpect(jsonPath("$.data.status", is(2)));
	}

	@Test
	public void whenDeleteTodo_thenStatus200() throws Exception {
		Todo savedTodo = createTestTodo("Todo Item 1", TodoStatus.CREATED);
		mvc.perform(delete("/todo/{todoId}", savedTodo.getTodoId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data", is(true)))
				.andExpect(jsonPath("$.apiStatus", is("DELETED"))).andExpect(jsonPath("$.success", is(true)));
	}

	@Test
	public void whenNoTodItems_thenStatus404() throws Exception {
		mvc.perform(get("/todo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.apiStatus", is("NOT_FOUND"))).andExpect(jsonPath("$.success", is(false)));
	}

	private Todo createTestTodo(String name, TodoStatus todoStatus) {
		Todo todo = new Todo();
		todo.setName(name);
		todo.setStatus(todoStatus);
		Todo createdTodo = this.todoRepository.save(todo);

		return createdTodo;
	}

	@AfterEach
	private void teardown() {
		this.todoRepository.deleteAll();
	}
}
