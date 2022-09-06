package web.spring_boot.dao;

import web.spring_boot.model.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);

    List<User> listUsers();

    User getUser(Long id);

    void editUser(User user);

    void deleteUser(Long id);
}