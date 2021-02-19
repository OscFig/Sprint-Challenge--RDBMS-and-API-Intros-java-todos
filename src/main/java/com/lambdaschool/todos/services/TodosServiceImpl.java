package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService{

    @Autowired
    private TodosRepository todosrepos;

    @Autowired
    private UserService userService;

    @Override
    public Todo save(long userid, Todo newTodo)
    {
        User currentuser = userService.findUserById(userid);
        Todo saveTodo = new Todo(currentuser, newTodo.getDescription());
        todosrepos.save(saveTodo);
        return saveTodo;
    }

    @Override
    public Todo markComplete(long todoid)
    {
        Todo updateTodo = todosrepos.findById(todoid).orElseThrow(() -> new EntityNotFoundException("Todo Item " + todoid + " Not Found"));
        updateTodo.setCompleted(true);
        return todosrepos.save(updateTodo);
    }
}
