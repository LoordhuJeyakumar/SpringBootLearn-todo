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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public void printAllTodos (){
        System.out.println(todoRepository.findAll());
    }


    public void saveTodo (Todo todo     ){

        todoRepository.save(todo);

    }


    //Get all todos
    public Iterable<Todo> getAllTodos(){
        return todoRepository.findAll();
    }


}

