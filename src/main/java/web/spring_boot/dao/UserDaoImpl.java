package web.spring_boot.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import web.spring_boot.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {

        return entityManager.createQuery(" FROM User").getResultList();

    }

    @Override
    public User getUser(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void editUser(User user) {


        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {

        entityManager.remove(entityManager.find(User.class, id));
    }
}