package com.example.startSpring;

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
    public String getTodos (){
        return "All availabale todos";
    }

    //Path Variable

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

    // Post create todo
    @PostMapping("/create")
    public String createTodo (@RequestBody String todo){
        return "Create todo" + " " + todo;
    }

    @PostMapping
    public String addTodo(@RequestBody String task) {
        todoService.addTodo(task);
        return "Todo added";
    }



}
