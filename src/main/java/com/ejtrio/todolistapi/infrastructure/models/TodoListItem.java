package com.ejtrio.todolistapi.infrastructure.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Entity
@Table(schema = "TLA", name = "TODO_LIST_ITEM")
@Data
public class TodoListItem extends AuditInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_LIST_ITEM_ID")
    private Long todoListItemId;

    @ManyToOne
    @JoinColumn(name = "TODO_LIST_ID")
    private TodoList todoList;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;

    @Column(name = "COMPLETED")
    private Boolean completed;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;

    @PrePersist
    @PreUpdate
    void updateItemCompletion() {
        if (!nonNull(completed)) {
            completed = Boolean.FALSE;
            completedAt = null;
        } else if (!completed) {
            completedAt = null;
        } else {
            completedAt = LocalDateTime.now();
        }
    }
}
