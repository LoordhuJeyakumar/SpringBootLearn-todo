package com.example.startSpring;

import com.example.startSpring.models.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //Get all todos

    @GetMapping("/all")
    public ResponseEntity<List<Todo>>getAllTodos(){
        try {
            log.info("Calling API endpont to get all todos /api/v1/todos/all");
            List<Todo> listOfTodos;
            listOfTodos = todoService.getAllTodos();

            return new ResponseEntity<>(listOfTodos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting all todos: " + e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all/pagination")
    public ResponseEntity<Page<Todo>> getAllTodosWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "completed") String sortBy
    ) {
        try {
            log.info("Calling API endpoint to get all todos with pagination ");

            Page<Todo> todos = todoService.getAllTodosWithPagination(page, size, sortBy);

            return new ResponseEntity<>(todos, HttpStatus.OK);


        } catch (Exception e) {
            log.error("Error getting all todos with pagination: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //Get Todo by id
    @GetMapping("/{todoId}")
    public String getTodoById(@PathVariable("todoId") String todoId){
        return "Get todo by id" + " " + todoId;
    }


    //Get Todo by user with RequestParams
    @GetMapping("/byUser")
    public String getTodosByUser(@RequestParam String user, @RequestParam String status, @RequestParam String priority) {
        return "Get todo by user" + " " + user + " " + status + " " + priority;
    }


    public String createTodo (@RequestBody String todo){
        return "Create todo" + " " + todo;
    }

    // Post create todo with error handling
    @PostMapping("/create")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo    ) {

       return new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.CREATED);


    }


    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.editTodoById(todoId, todo);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //update status of the todo
    @PatchMapping("/{todoId}/status")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable Long todoId, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.editTodoById(todoId, todo);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }



    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        try {
            todoService.deleteTodo(todoId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting todo", e);
        }
    }



}
