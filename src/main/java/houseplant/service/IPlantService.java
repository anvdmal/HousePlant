package houseplant.service;

import houseplant.dto.InputPlantDTO;
import houseplant.dto.OutputPlantDTO;

import java.util.List;
import java.util.UUID;

public interface IPlantService {
    OutputPlantDTO getById(UUID id);
    OutputPlantDTO add(InputPlantDTO dto);
    OutputPlantDTO watering(UUID id);
    OutputPlantDTO activate(UUID id);
    OutputPlantDTO wilt(UUID id);
    List<OutputPlantDTO> getAllAlive();
    List<OutputPlantDTO> getAllWilted();
}