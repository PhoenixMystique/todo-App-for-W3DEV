package com.devwithadi.todolist.Modules;

public class tasksdata {
    private int userId,id;
    private String title,body;

    public tasksdata(int userID, int id, String title, String body) {
        this.userId = userID;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserID() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setUserID(int userID) {
        this.userId = userID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
