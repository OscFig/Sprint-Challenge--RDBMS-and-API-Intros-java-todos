package com.lambdaschool.todos.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


//  The entity allowing interaction with the todos table.
@Entity
@Table(name = "todos")
public class Todos extends Auditable {

    //  Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private boolean completed = false;

    //  Relationships

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "todos", allowSetters = true)
    private User user;

    //  Default Constructor used primarily by the JPA
    public Todos() {
    }

    public Todos(User user, String description) {
        this.description = description;
        this.user = user;
    }

    //  Getters/Setters

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
