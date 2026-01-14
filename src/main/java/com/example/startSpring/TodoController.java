package com.example.startSpring;

import com.example.startSpring.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //Get all todos

    @GetMapping("/all")
    public ResponseEntity<Todo> getAllTodos(){
        try {
            return new ResponseEntity<Todo>((Todo) todoService.getAllTodos(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error getting all todos: " + e.getMessage());
            return new ResponseEntity<Todo>(HttpStatus.INTERNAL_SERVER_ERROR);
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
