package houseplant.repository;

import houseplant.entity.Watering;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WateringRepository extends BaseRepository<Watering, UUID> {
    public WateringRepository() {
        super(Watering.class);
    }
}