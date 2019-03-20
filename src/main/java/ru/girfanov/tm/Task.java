package ru.girfanov.tm;

public class Task {
    private int id;
    private String name;
    private int projectID;

    public Task() {
    }

    public Task(int id, String name, int projectID) {
        this.id = id;
        this.name = name;
        this.projectID = projectID;
    }

    public int getId() {
        return id;
    }

    public Task getById(int id) {
        return this;
    }

    public String getName() {
        return name;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
