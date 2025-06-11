package houseplant.repository;

import houseplant.entity.Plant;
import houseplant.entity.enums.PlantStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PlantRepository extends BaseRepository<Plant, UUID> {
    @PersistenceContext
    private EntityManager entityManager;

    public PlantRepository() {
        super(Plant.class);
    }

    public List<Plant> findByStatus(PlantStatus status) {
        return entityManager.createQuery("select p from Plant p where p.plantStatus = :status", Plant.class)
                .setParameter("status", status)
                .getResultList();
    }
}