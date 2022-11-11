package com.ejtrio.todolistapi.infrastructure.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(schema = "TLA", name = "TODO_LIST")
@Data
public class TodoList extends AuditInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_LIST_ID")
    private Long todoListId;

    @Column(name = "LIST_NAME")
    private String listName;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "todoList")
    private Collection<TodoListItem> items;
}
