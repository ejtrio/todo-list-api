package com.ejtrio.todolistapi.infrastructure.models.projections;

import com.ejtrio.todolistapi.infrastructure.models.TodoList;
import com.ejtrio.todolistapi.infrastructure.models.TodoListItem;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "todoListDetail", types = {TodoList.class})
public interface TodoListDetail {

    UserDetail getUser();

    String getListName();

    Collection<TodoListItem> getItems();
}
