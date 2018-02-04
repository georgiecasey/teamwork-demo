package com.georgiecasey.teamworkdemofragments.model.request.tasklists;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReorderTasklists {

    @SerializedName("todo-lists")
    @Expose
    private List<TodoList> todoLists = new ArrayList<TodoList>();

    public List<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    public void addTasklist(long id) {
        TodoList_ todoList_=new TodoList_();
        todoList_.setId(String.valueOf(id));
        TodoList todoList=new TodoList();
        todoList.setTodoList(todoList_);
        todoLists.add(todoList);
    }

}
