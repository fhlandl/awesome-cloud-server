package fhlandl.awesome_cloud_server.repository;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageRepository {

//    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public Storage save(Storage item) {
        em.persist(item);
        return item;
    }

    public Storage findOne(Long id) {
        return em.find(Storage.class, id);
    }

    public List<Storage> findAll(long userId) {
        String jpql = "select s from Storage s where s.user.id = :userId";
        TypedQuery<Storage> query = em.createQuery(jpql, Storage.class);
        return query.setParameter("userId", userId).getResultList();

    }
}
