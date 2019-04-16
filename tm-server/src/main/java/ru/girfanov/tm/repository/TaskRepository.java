package ru.girfanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

import java.util.List;

public interface TaskRepository extends ITaskRepository {

    @NotNull String TABLE = "app_task";
    @NotNull String ID = "id";
    @NotNull String NAME = "name";
    @NotNull String DESCRIPTION = "description";
    @NotNull String STATUS = "status_task";
    @NotNull String DATE_START = "date_start";
    @NotNull String DATE_END = "date_end";
    @NotNull String USER_ID = "user_id";
    @NotNull String PROJECT_ID = "project_id";

    @Insert("INSERT INTO " + TABLE + " (" + ID + ", " + NAME + ", " + DESCRIPTION + ", " + STATUS + ", " + DATE_START + ", " + DATE_END + ", " + USER_ID + ", " + PROJECT_ID + ") " +
            "VALUES (#{id}, #{name}, #{description}, #{status}, #{dateStart}, #{dateEnd}, #{userId}, #{projectId})")
    void persist(@NotNull final Task task);

    @Update("UPDATE " + TABLE + " SET " + NAME + " = #{name}, " + DESCRIPTION + " = #{description}, " + STATUS + " = #{status}, " + DATE_START + " = #{dateStart}, " + DATE_END + " = #{dateEnd}, " + USER_ID + " = #{userId}, " + PROJECT_ID + " = #{projectId} WHERE " + ID + " = #{id}")
    void merge(@NotNull final Task task);

    @Delete("DELETE FROM " + TABLE + " WHERE " + ID + " = (#{id}")
    void remove(@NotNull final Task task);

    @Delete("DELETE FROM " + TABLE + " WHERE " + USER_ID + " = #{userId}")
    void removeAllByUserId(@NotNull final String userId);

    @Select("SELECT * FROM " + TABLE + " WHERE " + ID + " = #{id} AND " + USER_ID + " = #{userId}")
    @Results({
            @Result(id=true, property="status", column="status_task"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id"),
            @Result(property="projectId", column="project_id")
    })
    Task findOne(@NotNull final String userId, @NotNull final String taskId);

    @Select("SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = #{userId}")
    @Results({
            @Result(id=true, property="status", column="status_task"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id"),
            @Result(property="projectId", column="project_id")
    })
    List<Task> findAllByUserId(@NotNull final String userId);

    @Select("SELECT * FROM " + TABLE)
    @Results({
            @Result(id=true, property="status", column="status_task"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id"),
            @Result(property="projectId", column="project_id")
    })
    List<Task> findAll();

    @Select("SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = #{userId} AND " + PROJECT_ID + " = #{projectId}")
    @Results({
            @Result(id=true, property="status", column="status_task"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id"),
            @Result(property="projectId", column="project_id")
    })
    List<Task> findAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId);

    @Delete("DELETE FROM " + TABLE + " WHERE " + USER_ID + " = #{userId} AND " + PROJECT_ID + " = #{projectId}")
    void removeAllTasksByProjectId(@NotNull final String userId, @NotNull final String projectId);

    @Select("SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = #{userId} ORDER BY #{value}")
    List<Task> findAllSortedByValue(@NotNull final String userId, @NotNull @Param("value") final String value);
}
