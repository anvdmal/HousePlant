package houseplant.service;

import houseplant.dto.InputPlantDTO;
import houseplant.dto.OutputPlantDTO;
import houseplant.entity.Plant;
import houseplant.entity.Watering;
import houseplant.entity.enums.PlantStatus;
import houseplant.entity.enums.SunlightType;
import houseplant.repository.PlantRepository;
import houseplant.repository.WateringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PlantService implements IPlantService {
    private PlantRepository plantRepository;
    private WateringRepository wateringRepository;

    @Autowired
    public void setPlantRepository(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Autowired
    public void setWateringRepository(WateringRepository wateringRepository) {
        this.wateringRepository = wateringRepository;
    }

    @Override
    public OutputPlantDTO getById(UUID id) {
        return mapToPlantDTO(plantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Растение с таким id не найдено.")));
    }

    @Override
    public OutputPlantDTO add(InputPlantDTO dto) {
        if (dto.getName() == null || dto.getPlantDate() == null || dto.getSunlightType() == null) {
            throw new IllegalArgumentException("Парматеры растения при создании не могут быть пустыми.");
        }

        Plant newPlant = new Plant(
                dto.getName(),
                dto.getPlantDate(),
                SunlightType.valueOf(dto.getSunlightType().toUpperCase()));

        plantRepository.create(newPlant);
        return mapToPlantDTO(newPlant);
    }

    @Override
    public OutputPlantDTO watering(UUID id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Растение с таким id не найдено."));
        Watering newWatering = new Watering(plant, LocalDate.now());
        wateringRepository.create(newWatering);
        return mapToPlantDTO(plant);
    }

    @Override
    public OutputPlantDTO activate(UUID id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Растение с таким id не найдено."));
        plant.markAsAlive();
        plantRepository.update(plant);
        return mapToPlantDTO(plant);
    }

    @Override
    public OutputPlantDTO wilt(UUID id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Растение с таким id не найдено."));
        plant.markAsWilted();
        plantRepository.update(plant);
        return mapToPlantDTO(plant);
    }

    @Override
    public List<OutputPlantDTO> getAllAlive() {
        return plantRepository.findByStatus(PlantStatus.ALIVE)
                .stream()
                .map(this::mapToPlantDTO)
                .toList();
    }

    @Override
    public List<OutputPlantDTO> getAllWilted() {
        return plantRepository.findByStatus(PlantStatus.WILTED)
                .stream()
                .map(this::mapToPlantDTO)
                .toList();
    }

    private OutputPlantDTO mapToPlantDTO(Plant plant) {
        LocalDate lastWatered = plant.getWateringHistory().stream()
                .map(Watering::getWateredDate)
                .max(LocalDate::compareTo)
                .orElse(null);

        int daysSinceWatered = (lastWatered != null)
                ? (int) ChronoUnit.DAYS.between(lastWatered, LocalDate.now())
                : 0;

        return new OutputPlantDTO(plant.getId(), plant.getName(), plant.getPlantDate(), plant.getPlantStatus().getValue(),
                plant.getSunlightType().getValue(), lastWatered, daysSinceWatered);
    }
}