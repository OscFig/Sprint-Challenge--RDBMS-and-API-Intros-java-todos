package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService
{
    @Autowired
    private TodosRepository todosrepos;

    @Autowired
    UserService userService;

    @Override
    public Todos save(long userid, Todos newTodo)
    {
        User currentUser = userService.findUserById(userid);
        Todos saveTodo = new Todos(currentUser, newTodo.getDescription());
        todosrepos.save(saveTodo);
        return saveTodo;
    }

    @Override
    public Todos markComplete(long todoid)
    {
        Todos updateTodo = todosrepos.findById(todoid).orElseThrow(() -> new EntityNotFoundException("Todos Item " + todoid + " Not Found"));
        updateTodo.setCompleted(true);
        return todosrepos.save(updateTodo);
    }
}