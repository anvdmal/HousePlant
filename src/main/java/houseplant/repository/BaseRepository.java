package houseplant.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public abstract class BaseRepository<Entity, UUID> {
    @PersistenceContext
    private EntityManager entityManager;

    private final Class<Entity> entityClass;

    public BaseRepository(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public long count() {
        return entityManager.createQuery("select count(*) from " + entityClass.getName(), Long.class)
                .getSingleResult();
    }

    @Transactional
    public Entity create(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public void update(Entity entity) {
        entityManager.merge(entity);
    }

    public Optional<Entity> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public Optional<Entity> findByName(String name) {
        List<Entity> results = entityManager.createQuery("select e from " + entityClass.getName() + " e where e.name = :name", entityClass)
                .setParameter("name", name)
                .getResultList();

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<Entity> findAll() {
        return entityManager.createQuery("from " + entityClass.getName(), entityClass)
                .getResultList();
    }
}