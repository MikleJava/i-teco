package ru.girfanov.tm.info;

public class Information {
    public void getInfo() {
        System.out.println("-cp\t create project \n" +
                "-ct\t create task \n" +
                "-up\t update project \n" +
                "-ut\t update task \n" +
                "-dp\t delete project \n" +
                "-dt\t delete task \n" +
                "-spbi\t select project by id \n" +
                "-stbi\t select task by id \n" +
                "-sap\t select all projects \n" +
                "-sat\t select all tasks");
    }
}
