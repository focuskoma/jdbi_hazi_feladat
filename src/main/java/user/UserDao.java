package user;
import org.jdbi.v3.sqlobject.config.*;
import org.jdbi.v3.sqlobject.customizer.*;
import org.jdbi.v3.sqlobject.statement.*;
import java.util.*;

@RegisterBeanMapper(User.class)
public interface UserDao {
    @SqlUpdate("""
        CREATE TABLE user (
            id IDENTITY PRIMARY KEY,
            username VARCHAR NOT NULL,
            password VARCHAR NOT NULL,
            name VARCHAR NOT NULL,
            email VARCHAR NOT NULL,
            gender ENUM('MALE', 'FEMALE') NOT NULL,
            birthDate DATE NOT NULL,
            enabled BOOLEAN
        )
        """
    )
    void createTable();

    @SqlUpdate("INSERT INTO user (id, username, password, name, email, gender, birthDate, enabled) VALUES (:id, :username, :password, :name, :email, :gender, :birthDate, :enabled)")
    @GetGeneratedKeys
    Long insert(@BindBean User user);

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    Optional<User> findById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM user WHERE username = :username")
    Optional<User> findByUsername(@Bind("username") String username);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void delete(@BindBean User user);

    @SqlQuery("SELECT * FROM user")
    List<User> list();
}