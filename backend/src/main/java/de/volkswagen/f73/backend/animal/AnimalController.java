package de.volkswagen.f73.backend.animal;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * The type Animal controller.
 */
@RestController
@AllArgsConstructor
public class AnimalController {

    private AnimalService service;

    /**
     * Gets all animals.
     *
     * @return the all animals
     */
    @GetMapping("/zoo/animals")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> allAnimals = service.getAllAnimals();
        return allAnimals.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allAnimals);
    }

    /**
     * Gets animal by id.
     *
     * @param id the id
     * @return the animal by id
     */
    @GetMapping("/zoo/animal/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Optional<Animal> animalById = service.getAnimalById(id);
        return animalById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Add animal response entity.
     *
     * @param animalDTO the animalDTO
     * @return the response entity
     */
    @PostMapping("/zoo/animal")
    public ResponseEntity<Animal> addAnimal(@Valid @RequestBody AnimalDTO animalDTO) {
        Optional<Animal> optionalAnimal = service.addAnimal(animalDTO);
        return optionalAnimal.map(value
                -> ResponseEntity.created(URI.create("/zoo/animals")).body(value)).orElseGet(()
                -> ResponseEntity.badRequest().build());
    }

    /**
     * Delete animal response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/zoo/animal/{id}")
    public ResponseEntity<Long> deleteAnimal(@PathVariable Long id) {
        return service.deleteAnimal(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Put animal response entity.
     *
     * @param animalDTO the animalDTO
     * @return the response entity
     */
    @PutMapping("/zoo/animal")
    public ResponseEntity<Animal> putAnimal(@RequestBody @Valid AnimalDTO animalDTO) {
        if (animalDTO.getId() != null && service.isAnimalAlreadyExists(animalDTO.getId())) {
            Optional<Animal> optionalAnimal = service.updateAnimal(animalDTO);
            return ResponseEntity.ok(optionalAnimal.get());

        }
        Optional<Animal> optionalAnimal = service.updateAnimal(animalDTO);
        return ResponseEntity.created(URI.create("/zoo/animals")).body(optionalAnimal.get());
    }

}
