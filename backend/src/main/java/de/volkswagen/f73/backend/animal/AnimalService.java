package de.volkswagen.f73.backend.animal;

import de.volkswagen.f73.backend.employee.EmployeeRepository;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Animal service.
 */
@Service
@AllArgsConstructor
public class AnimalService {

    private AnimalRepository animalRepository;
    private EmployeeRepository employeeRepository;
    private EnclosureRepository enclosureRepository;
    private AnimalMapper animalMapper;

    /**
     * Gets all animals.
     *
     * @return the all animals
     */
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.findAll()
                .stream().map(animal -> animalMapper.convertAnimalToDTO(animal)).collect(Collectors.toList());
    }

    /**
     * Add animal optional.
     *
     * @param animalDTO the animal dto
     * @return the optional
     */
    public Optional<Animal> addAnimal(AnimalDTO animalDTO) {
        Animal animalToAdd = animalMapper.convertDTOtoAnimal(animalDTO, enclosureRepository, employeeRepository);
        return Optional.of(animalRepository.save(animalToAdd));
    }

    /**
     * Delete animal boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteAnimal(Long id) {
        if (isAnimalAlreadyExists(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Is animal already exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean isAnimalAlreadyExists(Long id) {
        return animalRepository.existsById(id);
    }

    /**
     * Update animal optional.
     *
     * @param animalDTO the animal dto
     * @return the optional
     */
    public Optional<Animal> updateAnimal(AnimalDTO animalDTO) {
        Animal animalToUpdate = animalMapper.convertDTOtoAnimal(animalDTO, enclosureRepository, employeeRepository);
        return Optional.of((animalRepository.save(animalToUpdate)));
    }

    /**
     * Gets animal by id.
     *
     * @param id the id
     * @return the animal by id
     */
    public Optional<AnimalDTO> getAnimalById(Long id) {
        Optional<Animal> getOptionalAnimal = animalRepository.findById(id);
        if(getOptionalAnimal.isEmpty()){
            throw new NoSuchAnimalException("gibt das Animal nicht");
        }
        return getOptionalAnimal.map(animal -> animalMapper.convertAnimalToDTO(animal));
    }

//    /**
//     * Convert dt oto animal animal.
//     *
//     * @param animalDTO the animal dto
//     * @return the animal
//     */
//    public Animal convertDTOtoAnimal(AnimalDTO animalDTO) {
//        Animal animalToReturn = Animal.builder().name(animalDTO.getName())
//                .species(animalDTO.getSpecies())
//                .subsistenceCosts(animalDTO.getSubsistenceCosts())
//                .build();
//        if (animalDTO.getEnclosure() != null) {
//            animalToReturn.setEnclosure(enclosureRepository.findById(animalDTO.getEnclosure()).get());
//        }
//        if (animalDTO.getVet() != null) {
//            animalToReturn.setVet(employeeRepository.findById(animalDTO.getVet()).get());
//        }
//        if (animalDTO.getId() != null) {
//            animalToReturn.setId(animalDTO.getId());
//        }
//        return animalToReturn;
//    }


}
