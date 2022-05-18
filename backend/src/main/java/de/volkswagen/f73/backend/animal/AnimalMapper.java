package de.volkswagen.f73.backend.animal;

import de.volkswagen.f73.backend.employee.Employee;
import de.volkswagen.f73.backend.employee.EmployeeRepository;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class AnimalMapper {

    public Animal convertDTOtoAnimal(AnimalDTO animalDTO, EnclosureRepository enclosureRepository, EmployeeRepository employeeRepository) {
        Animal animalToReturn = Animal.builder().name(animalDTO.getName())
                .species(animalDTO.getSpecies())
                .subsistenceCosts(animalDTO.getSubsistenceCosts())
                .build();
        setEncloseForAnimal(animalDTO, enclosureRepository, animalToReturn);
        setVetForAnimal(animalDTO, employeeRepository, animalToReturn);
        setIdForAnimal(animalDTO, animalToReturn);
        return animalToReturn;
    }

    public AnimalDTO convertAnimalToDTO(Animal animal) {
        AnimalDTO animalDTOReturn =AnimalDTO.builder()
                .id(animal.getId())
                .name(animal.getName())
                .species(animal.getSpecies())
                .subsistenceCosts(animal.getSubsistenceCosts())
                .build();
        setEnClosureForAnimalDTO(animal, animalDTOReturn);
        setVetForANimalDTO(animal, animalDTOReturn);
        return animalDTOReturn;
    }

    private void setVetForANimalDTO(Animal animal, AnimalDTO animalDTO) {
        if(animal.getVet() != null ){
            animalDTO.setVet(animal.getVet().getId());
        }
    }

    private void setEnClosureForAnimalDTO(Animal animal, AnimalDTO animalDTO) {
        if(animal.getEnclosure() != null ){
            animalDTO.setEnclosure(animal.getEnclosure().getId());
        }
    }

    private void setIdForAnimal(AnimalDTO animalDTO, Animal animal) {
        if (animalDTO.getId() != null) {
            animal.setId(animalDTO.getId());
        }
    }

    private void setVetForAnimal(AnimalDTO animalDTO, EmployeeRepository employeeRepository, Animal animal) {
        if (animalDTO.getVet() != null ) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(animalDTO.getVet());
            optionalEmployee.ifPresent(animal::setVet);
        }
    }

    private void setEncloseForAnimal(AnimalDTO animalDTO, EnclosureRepository enclosureRepository, Animal animalToReturn) {
        if (animalDTO.getEnclosure() != null) {
            animalToReturn.setEnclosure(enclosureRepository.findById(animalDTO.getEnclosure()).get());
        }
    }
}
