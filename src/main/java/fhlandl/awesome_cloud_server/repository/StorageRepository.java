package fhlandl.awesome_cloud_server.repository;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.dto.StorageDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Storage findOneWithChildren(Long id) {
        String jpql = "select s from Storage s" +
            " left join fetch s.children where s.id = :id";
        return em.createQuery(jpql, Storage.class)
            .setParameter("id", id)
            .getSingleResult();
    }

    public List<Storage> findAll(StorageSearchCondition cond) {
        String jpql = "select s from Storage s";

        Long userId = cond.getUserId();
        Boolean includeDeleted = cond.getIncludeDeleted();

        if (userId != null || includeDeleted != null) {
            jpql += " where";
        }

        boolean andFlag = false;

        if (userId != null) {
            jpql += " s.user.id = :userId";
            andFlag = true;
        }

        if (includeDeleted != null && !includeDeleted) {
            if (andFlag) {
                jpql += " and";
            }
            jpql += " s.isDeleted = false";
        }

        TypedQuery<Storage> query = em.createQuery(jpql, Storage.class);
        return query.setParameter("userId", userId).getResultList();
    }

    public List<StorageDto> findOneAndDescendants(Long id) {
        String sql = "WITH RECURSIVE STORAGE_R(ID, PARENT_ID, NAME) AS (" +
            " SELECT ID, PARENT_ID, NAME" +
            " FROM STORAGE" +
            " WHERE ID = :id" +
            " UNION ALL" +
            " SELECT S.ID, S.PARENT_ID, S.NAME" +
            " FROM STORAGE S" +
            " JOIN STORAGE_R R ON S.PARENT_ID = R.ID" +
            ")" +
            " SELECT ID, PARENT_ID, NAME" +
            " FROM STORAGE_R";
        Query query = em.createNativeQuery(sql);
        List<Object[]> results = query.setParameter("id", id).getResultList();

        return results.stream()
            .map(row -> new StorageDto(
                (Long) row[0],
                (Long) row[1],
                (String) row[2]
            ))
            .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        Storage storage = em.find(Storage.class, id);
        if (storage != null) {
            em.remove(storage);
        }
    }
}
