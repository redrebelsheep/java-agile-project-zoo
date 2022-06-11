package de.volkswagen.f73.backend.enclosure;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/** The type Enclosure controller. */
@RestController
@AllArgsConstructor
public class EnclosureController {

  private EnclosureService service;

  /**
   * Gets all enclosures.
   *
   * @return the all enclosures
   */
  @GetMapping("/zoo/enclosures")
  public ResponseEntity<List<EnclosureDTO>> getAllEnclosures() {
    List<EnclosureDTO> allEnclosures = service.getAllEnclosures();
    return allEnclosures.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(allEnclosures);
  }

  /**
   * Gets enclosure by id.
   *
   * @param id the id
   * @return the enclosure by id
   */
  @GetMapping("/zoo/enclosure/{id}")
  public ResponseEntity<EnclosureDTO> getEnclosureById(@PathVariable Long id) {
    Optional<EnclosureDTO> enclosureById = service.getEnclosureById(id);
    return enclosureById
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }

  /**
   * Add enclosure response entity.
   *
   * @param enclosureDTO the enclosureDTO
   * @return the response entity
   */
  @PostMapping("/zoo/enclosure")
  public ResponseEntity<Enclosure> addEnclosure(@RequestBody @Valid EnclosureDTO enclosureDTO) {
    Optional<Enclosure> optionalEnClosure = service.addEnclosure(enclosureDTO);
    return optionalEnClosure
        .map(value -> ResponseEntity.created(URI.create("/zoo/enclosures")).body(value))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  /**
   * Delete enclosure response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/zoo/enclosure/{id}")
  public ResponseEntity<Long> deleteEnclosure(@PathVariable Long id) {
    return service.deleteEnclosure(id)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
  }

  /**
   * Put enclosure response entity.
   *
   * @param enclosureDTO the enclosureDTO
   * @return the response entity
   */
  @PutMapping("/zoo/enclosure")
  public ResponseEntity<Enclosure> putEnclosure(@RequestBody @Valid EnclosureDTO enclosureDTO) {
    if (enclosureDTO.getId() != null && service.checkIfEnclosureExists(enclosureDTO.getId())) {
      Optional<Enclosure> optionalEnClosure = service.updateEnclosure(enclosureDTO);
      return ResponseEntity.ok(optionalEnClosure.get());
    }
    Optional<Enclosure> optionalEnClosure = service.updateEnclosure(enclosureDTO);
    return ResponseEntity.created(URI.create("/zoo/enclosures")).body(optionalEnClosure.get());
  }
}
