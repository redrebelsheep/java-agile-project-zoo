package de.volkswagen.f73.backend.stall;

import de.volkswagen.f73.backend.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Stall service.
 */
@Service
@AllArgsConstructor
public class StallService {

    private StallRepository stallRepository;
    private EmployeeRepository employeeRepository;
    private StallMapper stallMapper;

    /**
     * Gets all stalls.
     *
     * @return the all stalls
     */
    public List<StallDTO> getAllStalls() {
        return stallRepository.findAll()
                .stream().map(stall -> stallMapper.covertStallToDTO(stall)).collect(Collectors.toList());
    }

    /**
     * Gets stall by id.
     *
     * @param id the id
     * @return the stall by id
     */
    public Optional<StallDTO> getStallById(Long id) {
        Optional<Stall> optionalStall = stallRepository.findById(id);
        return optionalStall.map(stall -> stallMapper.covertStallToDTO(stall));
    }

    /**
     * Add stall optional.
     *
     * @param stallDTO the stall
     * @return the optional
     */
    public Optional<Stall> addStall(StallDTO stallDTO) {
        Stall stallToAdd = stallMapper.convertDTOtoStall(stallDTO, employeeRepository);
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
        Stall updateStall = stallMapper.convertDTOtoStall(stallDTO, employeeRepository);
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



}
