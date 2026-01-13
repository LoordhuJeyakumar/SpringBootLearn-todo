//package com.example.startSpring;
//
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class TodoRepository {
//    String getAllTodos() {
//        return "Get all todos from db";
//    }
//}
//

package com.example.startSpring;

import com.example.startSpring.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/* * @Repository tells Spring that this interface is responsible
 * for communicating with the MySQL database.
 */
@Repository // Tells Spring this is the Data Access layer
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // We 'extend' JpaRepository to get free methods:
    // .findAll() -> SELECT * FROM todos
    // .save()    -> INSERT INTO or UPDATE todos
    // .delete()  -> DELETE FROM todos

    /*
     * You don't need to write any methods here yet!
     * * By extending 'JpaRepository<Todo, Long>', Spring gives you:
     * 1. save(Todo)          -> (INSERT or UPDATE in SQL)
     * 2. findAll()           -> (SELECT * FROM todos)
     * 3. findById(Long id)   -> (SELECT * FROM todos WHERE id = ?)
     * 4. deleteById(Long id) -> (DELETE FROM todos WHERE id = ?)
     * 5. count()             -> (SELECT COUNT(*) FROM todos)
     */
}