package houseplant.controller;

import houseplant.dto.InputPlantDTO;
import houseplant.dto.OutputPlantDTO;
import houseplant.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/house/plants")
@CrossOrigin(origins = "http://localhost:5173")
public class PlantController {
    private PlantService plantService;

    @Autowired
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/{id}")
    public OutputPlantDTO getPlant(@PathVariable UUID id) {
        return plantService.getById(id);
    }

    @GetMapping("/alive")
    public List<OutputPlantDTO> getActivePlants() {
        return plantService.getAllAlive();
    }

    @GetMapping("/wilted")
    public List<OutputPlantDTO> getWiltedPlants() {
        return plantService.getAllWilted();
    }

    @PostMapping("/add")
    public OutputPlantDTO add(@RequestBody InputPlantDTO dto) {
        return plantService.add(dto);
    }

    @PostMapping("/{id}/watering")
    public OutputPlantDTO wateringPlant(@PathVariable UUID id) {
        return plantService.watering(id);
    }

    @PostMapping("/{id}/wilt")
    public OutputPlantDTO wiltPlant(@PathVariable UUID id) {
        return plantService.wilt(id);
    }

    @PostMapping("/{id}/activate")
    public OutputPlantDTO activatePlant(@PathVariable UUID id) {
        return plantService.activate(id);
    }
}