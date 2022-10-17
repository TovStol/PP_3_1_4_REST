package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }

    public User getUserByName(String name) {
        Query query = entityManager.createQuery("FROM User u WHERE u.username = :name");
        query.setParameter("name", name);
        User user = (User) query.getSingleResult();
        entityManager.detach(user);
        return user;
    }

    public User getUserByEmail(String email) {
        Query query = entityManager.createQuery("FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        entityManager.detach(user);
        return user;
    }
}
