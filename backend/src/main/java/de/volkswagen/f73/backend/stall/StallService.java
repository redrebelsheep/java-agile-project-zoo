package de.volkswagen.f73.backend.stall;

import de.volkswagen.f73.backend.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Stall service.
 */
@Service
@AllArgsConstructor
public class StallService {

    private StallRepository stallRepository;
    private EmployeeRepository employeeRepository;

    /**
     * Gets all stalls.
     *
     * @return the all stalls
     */
    public List<Stall> getAllStalls() {
        return stallRepository.findAll();
    }

    /**
     * Gets stall by id.
     *
     * @param id the id
     * @return the stall by id
     */
    public Optional<Stall> getStallById(Long id) {
        return stallRepository.findById(id);
    }

    /**
     * Add stall optional.
     *
     * @param stallDTO the stall
     * @return the optional
     */
    public Optional<Stall> addStall(StallDTO stallDTO) {
        Stall stallToAdd = convertDTOtoStall(stallDTO);
        return Optional.of(stallRepository.save(stallToAdd));
    }


    /**
     * Delete stall boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteStall(Long id) {
        if (isStallAlreadyExists(id)) {
            stallRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Update stall optional.
     *
     * @param stallDTO the stallDTO
     * @return the optional
     */
    public Optional<Stall> updateStall(StallDTO stallDTO) {
        Stall updateStall = convertDTOtoStall(stallDTO);
        return Optional.of(stallRepository.save(updateStall));
    }

    /**
     * Is stall already exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean isStallAlreadyExists(Long id) {
        return stallRepository.existsById(id);
    }
    private Stall convertDTOtoStall(StallDTO stallDTO) {
        Stall stallToAdd = Stall.builder()
                .operatingCost(stallDTO.getOperatingCost())
                .type(stallDTO.getType())
                .build();
        if (stallDTO.getSeller() != null) {
            stallToAdd.setSeller(employeeRepository.findById(stallDTO.getSeller()).get());
        }
        if (stallDTO.getId() != null) {
            stallToAdd.setId(stallDTO.getId());
        }
        return stallToAdd;
    }

}
