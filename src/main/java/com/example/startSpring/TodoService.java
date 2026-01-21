//package com.example.startSpring;
//
////Plain Java
//public class TodoService {
//
//    private TodoRepository todoRepository;
//
//    public TodoService() {
//        todoRepository = new TodoRepository();
//    }
//
//    public void printAllTodos (){
//        System.out.println(todoRepository.getAllTodos());
//    }
//
//}


package com.example.startSpring;

import com.example.startSpring.models.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

//    public void saveTodo (Todo todo     ){
//
//        todoRepository.save(todo);
//
//    }

    //Save Todo with Error handling
    public Todo saveTodo(Todo todo) {
        try {
         return   todoRepository.save(todo);
        } catch (Exception e) {
            System.out.println("Error saving todo: " + e.getMessage());
            return null;
        }
    }


    //Get all todos
    public List<Todo> getAllTodos(){
       try{
           return todoRepository.findAll();
       } catch (Exception e) {
           System.out.println("Error getting all todos: " + e.getMessage());
           return null;
       }
    }


    //Get all todos with pagination
    public Page<Todo> getAllTodosWithPagination (int page, int size, String sortBy){
        try{
            Pageable pageable = PageRequest.of(page, size , Sort.by(sortBy));
            return todoRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("Error getting all todos with pagination: " + e.getMessage());
            return null;
        }

    }

    //edit todo by id with error handling
    public Todo editTodoById (Long id, Todo todo){
        try{
            Todo existingTodo;
            existingTodo = todoRepository.findById(id).orElse(null);

            if (existingTodo != null){
                existingTodo.setTitle(todo.getTitle());
                existingTodo.setDescription(todo.getDescription());
                existingTodo.setCompleted(todo.isCompleted());
                return todoRepository.save(existingTodo);
            } else {
                return null;
            }
            //return todoRepository.save(todo);
        }catch (Exception e ){
            System.out.println("Error editing todo: " + e.getMessage());
            return null;


        }
    }

    //Update status of the todo
    public Todo updateTodoStatus(Long id, Todo todo){
        try{
            Todo existingTodo;
            existingTodo = todoRepository.findById(id).orElse(null);

            if (existingTodo != null){
                existingTodo.setCompleted(todo.isCompleted());
                return todoRepository.save(existingTodo);
            } else {
                return null;
            }
        }catch (Exception e ){
            System.out.println("Error editing todo: " + e.getMessage());
            return null;
        }
    }


    //Delete todo
    public void deleteTodo(Long id){
        try{
            todoRepository.deleteById(id);



        } catch (Exception e) {
            System.out.println("Error deleting todo: " + e.getMessage());
            throw new RuntimeException("Error deleting todo");
        }
    }


}

