package de.volkswagen.f73.backend.enclosure;

import de.volkswagen.f73.backend.animal.Animal;
import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.employee.Employee;
import de.volkswagen.f73.backend.employee.EmployeeRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

public class EnclosureMapper {

    public Enclosure convertDTOtoEnclosure(EnclosureDTO enclosureDTO, EmployeeRepository employeeRepository,
                                            AnimalRepository animalRepository ) {
        Enclosure enclosure = Enclosure.builder()
                .name(enclosureDTO.getName())
                .maintenanceCosts(enclosureDTO.getMaintenanceCosts()).build();
        setStaffForEmployee(enclosureDTO, employeeRepository, enclosure);
        setAnimalsForEnclosure(enclosureDTO, animalRepository, enclosure);
        setIDForEnClosures(enclosureDTO, enclosure);
        return enclosure;
    }

    public EnclosureDTO convertEnclosureToDTO(Enclosure enclosure){
        EnclosureDTO enclosureDTO = EnclosureDTO
                .builder()
                .id(enclosure.getId())
                .name(enclosure.getName())
                .maintenanceCosts(enclosure.getMaintenanceCosts()).build();
        setStaffForDTO(enclosure, enclosureDTO);
        setAnimalsForDTO(enclosure, enclosureDTO);
        return enclosureDTO;
    }

    private void setStaffForDTO(Enclosure enclosure, EnclosureDTO enclosureDTO) {
        if(enclosureDTO.getStaff() != null && enclosureDTO.getStaff().isEmpty()){
            enclosureDTO.setStaff(enclosure.getStaff().stream().map(Employee::getId).collect(Collectors.toSet()));
        }
    }

    private void setAnimalsForDTO(Enclosure enclosure, EnclosureDTO enclosureDTO) {
        if(enclosureDTO.getAnimals() != null && enclosureDTO.getAnimals().isEmpty()){
            enclosureDTO.setAnimals(enclosure.getAnimals().stream().map(Animal::getId).collect(Collectors.toSet()));
        }
    }

    private void setStaffForEmployee(EnclosureDTO enclosureDTO, EmployeeRepository employeeRepository, Enclosure enclosure) {
        if (enclosureDTO.getStaff() != null && !enclosureDTO.getStaff().isEmpty()) {
            enclosure.setStaff(new HashSet<>(employeeRepository.findAllById(enclosureDTO.getStaff())));
        }
    }

    private void setAnimalsForEnclosure(EnclosureDTO enclosureDTO, AnimalRepository animalRepository, Enclosure enclosure) {
        if (enclosureDTO.getAnimals() != null && !enclosureDTO.getAnimals().isEmpty()) {
            enclosure.setAnimals(new HashSet<>(animalRepository.findAllById(enclosureDTO.getAnimals())));
        }
    }

    private void setIDForEnClosures(EnclosureDTO enclosureDTO, Enclosure enclosure) {
        if (enclosureDTO.getId() != null) {
            enclosure.setId(enclosureDTO.getId());
        }
    }

}
