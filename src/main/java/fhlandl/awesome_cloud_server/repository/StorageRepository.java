package fhlandl.awesome_cloud_server.repository;

import fhlandl.awesome_cloud_server.domain.Storage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StorageRepository {

    @PersistenceContext
    private EntityManager em;

    public Storage save(Storage item) {
        em.persist(item);
        return item;
    }

    public Storage findOne(Long id) {
        return em.find(Storage.class, id);
    }
}
