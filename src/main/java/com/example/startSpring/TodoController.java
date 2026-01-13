package com.example.startSpring;

import com.example.startSpring.models.Todo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //Get all todos

    @GetMapping("/all")
    public Iterable<Todo> getAllTodos(){
        return todoService.getAllTodos();
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

    // Post create todo
    @PostMapping("/create")
    public void addTodo(@RequestBody Todo todo    ) {

        todoService.saveTodo(todo);

    }



}
