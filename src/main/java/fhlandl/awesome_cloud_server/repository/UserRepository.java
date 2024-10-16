package fhlandl.awesome_cloud_server.repository;

import fhlandl.awesome_cloud_server.domain.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findOne(long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
            .getResultList();
    }

    public List<User> findByLoginId(String loginId) {
        return em.createQuery("select u from User u where u.loginId = :loginId", User.class)
            .setParameter("loginId", loginId)
            .getResultList();
    }
}
