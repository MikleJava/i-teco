package ru.girfanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.IUserRepository;
import ru.girfanov.tm.entity.User;

import java.util.List;

public interface UserRepository extends IUserRepository {

    @NotNull String TABLE = "app_user";
    @NotNull String ID = "id";
    @NotNull String LOGIN = "login";
    @NotNull String PASSWORD = "password_hash";
    @NotNull String ROLE = "role";

    @Insert("INSERT INTO " + TABLE + " (" + ID + ", " + LOGIN + ", " + PASSWORD + ", " + ROLE + ") " +
            "VALUES (#{id}, #{login}, #{password}, #{role})")
    void persist(@NotNull final User user);

    @Update("UPDATE " + TABLE + " SET " + LOGIN + " = #{login}, " + PASSWORD + " = #{password}, " + ROLE + " = #{role} WHERE " + ID + " = #{id}")
    void merge(@NotNull final User user);

    @Delete("DELETE FROM " + TABLE + " WHERE " + ID + " = #{id}")
    void remove(@NotNull final User user);

    @Select("SELECT * FROM " + TABLE + " WHERE " + ID + " = #{id}")
    @Results({@Result(id=true, property="password", column="password_hash")})
    User findOne(@NotNull @Param("id") final String id);

    @Select("SELECT * FROM " + TABLE)
    @Results({@Result(id=true, property="password", column="password_hash")})
    List<User> findAll();

    @Select("SELECT * FROM " + TABLE + " WHERE " + LOGIN + " = #{login} AND " + PASSWORD + " = #{password}")
    @Results({@Result(id=true, property="password", column="password_hash")})
    User findOneByLoginAndPassword(@NotNull @Param("login") final String login, @NotNull @Param("password") final String password);
}
