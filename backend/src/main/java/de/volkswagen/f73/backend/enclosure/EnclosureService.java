package de.volkswagen.f73.backend.enclosure;

import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.employee.Employee;
import de.volkswagen.f73.backend.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Enclosure service.
 */
@Service
@AllArgsConstructor
public class EnclosureService {

    private EnclosureRepository enclosureRepository;
    private EmployeeRepository employeeRepository;
    private AnimalRepository animalRepository;
    private EnclosureMapper enclosureMapper;

    /**
     * Gets all enclosures.
     *
     * @return the all enclosures
     */
    public List<EnclosureDTO> getAllEnclosures() {
        return enclosureRepository.findAll()
                .stream().map(enclosure -> enclosureMapper.convertEnclosureToDTO(enclosure)).collect(Collectors.toList());
    }


    /**
     * Gets enclosure by id.
     *
     * @param id the id
     * @return the enclosure by id
     */
    public Optional<EnclosureDTO> getEnclosureById(Long id) {
        Optional<Enclosure> optionalEnClosure = enclosureRepository.findById(id);
        return optionalEnClosure.map(enclosure -> enclosureMapper.convertEnclosureToDTO(enclosure));
    }

    /**
     * Add enclosure optional.
     *
     * @param enclosureDTO the enclosure dto
     * @return the optional
     */
    public Optional<Enclosure> addEnclosure(EnclosureDTO enclosureDTO) {

        Enclosure enclosureToAdd = enclosureMapper.convertDTOtoEnclosure(enclosureDTO,employeeRepository, animalRepository );

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
        Enclosure enclosureToUpdate =  enclosureMapper.convertDTOtoEnclosure(enclosureDTO,employeeRepository, animalRepository );
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

}