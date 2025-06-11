package houseplant;

import houseplant.entity.Plant;
import houseplant.entity.enums.SunlightType;
import houseplant.repository.PlantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitData implements CommandLineRunner {
    private PlantRepository plantRepository;

    public InitData(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(plantRepository.count() == 0) {
            initPlants();
        }
        System.out.println("Данные инициализированы!");
    }

    private void initPlants() {
        Plant plant1 = new Plant("Фикус", LocalDate.now().minusDays(5), SunlightType.PARTIAL);
        Plant plant2 = new Plant("Кактус", LocalDate.now().minusDays(10), SunlightType.SUN);
        Plant plant3 = new Plant("Папоротник", LocalDate.now().minusDays(15), SunlightType.SHADE);
        Plant plant4 = new Plant("Монстера", LocalDate.now().minusDays(20), SunlightType.PARTIAL);
        Plant plant5 = new Plant("Орхидея", LocalDate.now().minusDays(25), SunlightType.SUN);

        plantRepository.create(plant1);
        plantRepository.create(plant2);
        plantRepository.create(plant3);
        plantRepository.create(plant4);
        plantRepository.create(plant5);
    }
}