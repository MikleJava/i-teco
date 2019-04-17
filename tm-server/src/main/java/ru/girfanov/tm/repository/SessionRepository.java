package ru.girfanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ISessionRepository;
import ru.girfanov.tm.entity.Session;

import java.util.List;

public interface SessionRepository extends ISessionRepository {

    @NotNull String TABLE = "app_session";
    @NotNull String ID = "id";
    @NotNull String TIMESTAMP = "timestamp";
    @NotNull String SIGNATURE = "signature";
    @NotNull String USER_ID = "user_id";


    @Insert("INSERT INTO " + TABLE + " (" + ID + ", " + TIMESTAMP + ", " + SIGNATURE + ", " + USER_ID + ") " +
            "VALUES (#{id}, #{timestamp}, #{signature}, #{userId})")
    void persist(@NotNull final Session session);

    @Update("UPDATE " + TABLE + " SET " + TIMESTAMP + " = #{timestamp}, " + SIGNATURE + " = #{signature}, " + USER_ID + " = #{userId} WHERE " + ID + " = #{id}")
    void merge(@NotNull final Session session);

    @Delete("DELETE FROM " + TABLE + " WHERE " + ID + " = #{id}")
    void remove(@NotNull final Session session);

    @Delete("DELETE FROM " + TABLE + " WHERE " + USER_ID + " = #{userId}")
    void removeAllByUserId(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM " + TABLE + " WHERE " + ID + " = #{id} AND " + USER_ID + " = #{userId}")
    @Results({@Result(id=true, property="userId", column="user_id")})
    Session findOne(@NotNull @Param("userId") final String userId, @NotNull @Param("id") final String id);

    @Select("SELECT * FROM " + TABLE + " WHERE " + USER_ID + " = #{userId}")
    @Results({@Result(id=true, property="userId", column="user_id")})
    List<Session> findAllByUserId(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM " + TABLE)
    @Results({@Result(id=true, property="userId", column="user_id")})
    List<Session> findAll();
}
