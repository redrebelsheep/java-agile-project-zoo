package de.volkswagen.f73.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.volkswagen.f73.backend.animal.Animal;
import de.volkswagen.f73.backend.animal.AnimalRepository;
import de.volkswagen.f73.backend.employee.Employee;
import de.volkswagen.f73.backend.employee.EmployeeRepository;
import de.volkswagen.f73.backend.employee.Job;
import de.volkswagen.f73.backend.enclosure.Enclosure;
import de.volkswagen.f73.backend.enclosure.EnclosureRepository;
import de.volkswagen.f73.backend.stall.Stall;
import de.volkswagen.f73.backend.stall.StallRepository;
import de.volkswagen.f73.backend.stall.StallType;
import de.volkswagen.f73.backend.zoo_history.ZooHistory;
import de.volkswagen.f73.backend.zoo_history.ZooHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class BackendZooApplication implements CommandLineRunner {

/*    @Autowired
    private EnclosureRepository enclosureRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private StallRepository stallRepository;*/

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(BackendZooApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //FOR DATA :

/*        Enclosure enclosure = Enclosure.builder().name("Africa").maintenanceCosts(new BigDecimal("22.22")).build();
        Enclosure enclosure2 = Enclosure.builder().name("leerer").maintenanceCosts(new BigDecimal("22.22")).build();
        Employee employee = Employee.builder().name("Lars").job(Job.SELLER).Salary(new BigDecimal(2)).build();
        Employee employeeMarian = Employee.builder().name("mariantest").job(Job.KEEPER).Salary(new BigDecimal(2)).build();
        Employee validEmployee =  employeeRepository.save(employee);
         Enclosure validEnclosure = enclosureRepository.save(enclosure);
        Animal paul = Animal.builder().name("Paul").species("Eisbär").vet(validEmployee).enclosure(validEnclosure).subsistenceCosts(new BigDecimal("22.22")).build();
        Animal manfred = Animal.builder().name("manfred").species("Eisbär").vet(validEmployee).subsistenceCosts(new BigDecimal("22.22")).build();
        Animal manfredleer = Animal.builder().name("manfredleer").species("Eisbär").subsistenceCosts(new BigDecimal("22.22")).build();
        Stall validStall = Stall.builder().seller(validEmployee).operatingCost(new BigDecimal("22.22")).type(StallType.DRINK).build();
        Stall validStall2 = Stall.builder().operatingCost(new BigDecimal("22.22")).type(StallType.DRINK).build();
        validEnclosure.setAnimals(Set.of(paul));
        validEmployee.setResponsibilityAnimals(Set.of(paul));
        validEmployee.setStall(validStall);
        validEmployee.setEnclosures(Set.of(enclosure));
        validEnclosure.setStaff(Set.of(employee));
        animalRepository.save(manfred);
        animalRepository.save(manfredleer);
        employeeRepository.save(validEmployee);
        enclosureRepository.save(validEnclosure);
        enclosureRepository.save(enclosure2);
        stallRepository.save(validStall2);*/

        //-----------------FOR DATA

//        System.out.println("validPaul = " + validPaul);
//        Set<Animal> animals = new HashSet<>();
//        animals.add(paul);
//        System.out.println(validPaul);
//        ZooHistory history1 = ZooHistory.builder()
//                .date(LocalDate.now())
//                .salesPerDay(new BigDecimal("2"))
//                .visitorsPerDay(1112L)
//                .staffCount(1)
//                .build();
//
//        ZooHistory history2 = ZooHistory.builder()
//                .date(LocalDate.now())
//                .salesPerDay(new BigDecimal("2"))
//                .visitorsPerDay(1112L)
//                .staffCount(1)
//                .build();
//
//        ZooHistory history3 = ZooHistory.builder()
//                .date(LocalDate.now())
//                .salesPerDay(new BigDecimal("2"))
//                .visitorsPerDay(1112L)
//                .staffCount(1)
//                .build();
//
//        ZooHistory history4 = ZooHistory.builder()
//                .date(LocalDate.now())
//                .salesPerDay(new BigDecimal("2"))
//                .visitorsPerDay(1112L)
//                .staffCount(1)
//                .build();
//
//        List<ZooHistory> zooHistoryList = List.of(history1, history2, history3, history4);
//        System.out.println(mapper.writeValueAsString(zooHistoryList));
    }
}
