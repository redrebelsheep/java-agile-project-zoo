package de.volkswagen.f73.backend.enclosure;

import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.employee.EmployeeRepository;
import de.volkswagen.f73.backend.stall.Stall;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * The type Enclosure service.
 */
@Service
@AllArgsConstructor
public class EnclosureService {

    private EnclosureRepository enclosureRepository;
    private EmployeeRepository employeeRepository;
    private AnimalRepository animalRepository;

    /**
     * Gets all enclosures.
     *
     * @return the all enclosures
     */
    public List<Enclosure> getAllEnclosures() {
        return enclosureRepository.findAll();
    }

    /**
     * Add enclosure optional.
     *
     * @param enclosureDTO the enclosure dto
     * @return the optional
     */
    public Optional<Enclosure> addEnclosure(EnclosureDTO enclosureDTO) {

        Enclosure enclosureToAdd = convertDTOtoEnclosure(enclosureDTO);

        return Optional.of(enclosureRepository.save(enclosureToAdd));
    }

    /**
     * Delete enclosure boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteEnclosure(Long id) {
        if (checkIfEnclosureExists(id)) {
            enclosureRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Update enclosure optional.
     *
     * @param enclosureDTO the enclosure dto
     * @return the optional
     */
    public Optional<Enclosure> updateEnclosure(EnclosureDTO enclosureDTO) {
        Enclosure enclosureToUpdate = convertDTOtoEnclosure(enclosureDTO);
        return Optional.of(enclosureRepository.save(enclosureToUpdate));
    }

    /**
     * Check if enclosure exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean checkIfEnclosureExists(Long id) {
        return enclosureRepository.existsById(id);
    }

    /**
     * Gets enclosure by id.
     *
     * @param id the id
     * @return the enclosure by id
     */
    public Optional<Enclosure> getEnclosureById(Long id) {
        return enclosureRepository.findById(id);
    }

    private Enclosure convertDTOtoEnclosure(EnclosureDTO enclosureDTO) {
        Enclosure enclosure = Enclosure.builder()
                .name(enclosureDTO.getName())
                .maintenanceCosts(enclosureDTO.getMaintenanceCosts()).build();
        if (enclosureDTO.getStaff() != null && !enclosureDTO.getStaff().isEmpty()) {
            enclosure.setStaff(new HashSet<>(employeeRepository.findAllById(enclosureDTO.getStaff())));
        }
        if (enclosureDTO.getAnimals() != null && !enclosureDTO.getAnimals().isEmpty()) {
            enclosure.setAnimals(new HashSet<>(animalRepository.findAllById(enclosureDTO.getAnimals())));
        }
        if (enclosureDTO.getId() != null) {
            enclosure.setId(enclosureDTO.getId());
        }
        return enclosure;
    }
}
