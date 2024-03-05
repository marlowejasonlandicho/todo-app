package exam.todo.repository;

import org.springframework.data.repository.CrudRepository;

import exam.todo.entity.Todo;

/**
 * 
 * <p>
 * Data Repository interface for interacting with Database
 * </p>
 * 
 * 
 * @author Marlowe Landicho
 *
 * @version
 * 
 */
public interface TodoRepository extends CrudRepository<Todo, Long>{

}
