package com.georgiecasey.teamworkdemofragments.model.request.tasklists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodoList {

    @SerializedName("todo-list")
    @Expose
    private TodoList_ todoList;

    public TodoList_ getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList_ todoList) {
        this.todoList = todoList;
    }

}
