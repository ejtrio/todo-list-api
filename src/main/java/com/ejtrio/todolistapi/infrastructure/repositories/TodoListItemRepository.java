package com.ejtrio.todolistapi.infrastructure.repositories;

import com.ejtrio.todolistapi.infrastructure.models.TodoListItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TodoListItemRepository extends PagingAndSortingRepository<TodoListItem, Long> {
}
