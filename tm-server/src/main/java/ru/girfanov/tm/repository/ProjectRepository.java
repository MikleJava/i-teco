package ru.girfanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IProjectRepository;
import ru.girfanov.tm.entity.Project;

import java.util.List;

public interface ProjectRepository extends IProjectRepository {

    @NotNull String TABLE = "app_project";
    @NotNull String ID = "id";
    @NotNull String NAME = "name";
    @NotNull String DESCRIPTION = "description";
    @NotNull String STATUS = "status_project";
    @NotNull String DATE_START = "date_start";
    @NotNull String DATE_END = "date_end";
    @NotNull String USER_ID = "user_id";

    @Insert("INSERT INTO " + TABLE + " (" + ID + ", " + NAME + ", " + DESCRIPTION + ", " + STATUS + ", " + DATE_START + ", " + DATE_END + ", " + USER_ID + ") " +
            "VALUES (#{id}, #{name}, #{description}, #{status}, #{dateStart}, #{dateEnd}, #{userId})")
    void persist(@NotNull final Project project);

    @Update("UPDATE " + TABLE + " SET " + NAME + " = #{name}, " + DESCRIPTION + " = #{description}, " + STATUS + " = #{status}, " + DATE_START + " = #{dateStart}, " + DATE_END + " = #{dateEnd}, " + USER_ID + " = #{userId} WHERE " + ID + " = #{id}")
    void merge(@NotNull final Project project);

    @Delete("DELETE FROM " + TABLE + " WHERE " + ID + " = #{id}")
    void remove(@NotNull final Project project);

    @Delete("DELETE FROM " + TABLE + " WHERE " + USER_ID + " = #{userId}")
    void removeAllByUserId(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM " + TABLE + " WHERE " + ID + " = #{id} AND " + USER_ID + " = #{userId}")
    @Results({
            @Result(id=true, property="status", column="status_project"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id")
    })
    Project findOne(@NotNull @Param("userId") final String userId, @NotNull @Param("projectId") final String projectId);

    @Select("SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = #{userId}")
    @Results({
            @Result(id=true, property="status", column="status_project"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id")
    })
    List<Project> findAllByUserId(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM " + TABLE)
    @Results({
            @Result(id=true, property="status", column="status_project"),
            @Result(property="dateStart", column="date_start"),
            @Result(property="dateEnd", column="date_end"),
            @Result(property="userId", column="user_id")
    })
    List<Project> findAll();

    @Select("SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = #{userId} ORDER BY #{value}")
    List<Project> findAllSortedByValue(@NotNull @Param("userId") final String userId, @NotNull @Param("value") final String value);
}
