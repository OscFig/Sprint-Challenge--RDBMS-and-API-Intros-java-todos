package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;

public interface TodosService
{
    Todo save(long userid, Todo newTodo);

    Todo markComplete(long todoid);
}
