package com.ejtrio.todolistapi.infrastructure.repositories;

import com.ejtrio.todolistapi.infrastructure.models.TodoList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TodoListRepository extends PagingAndSortingRepository<TodoList, Long> {
}
