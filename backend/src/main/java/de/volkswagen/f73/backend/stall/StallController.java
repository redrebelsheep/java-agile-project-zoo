package de.volkswagen.f73.backend.stall;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * The type Stall controller.
 */
@RestController
@AllArgsConstructor
public class StallController {

    private StallService service;

    /**
     * Gets all stalls.
     *
     * @return the all stalls
     */
    @GetMapping("/zoo/stalls")
    public ResponseEntity<List<Stall>> getAllStalls() {
        List<Stall> allStalls = service.getAllStalls();
        return allStalls.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allStalls);
    }

    /**
     * Gets stall by id.
     *
     * @param id the id
     * @return the stall by id
     */
    @GetMapping("/zoo/stall/{id}")
    public ResponseEntity<Stall> getStallById(@PathVariable Long id) {
        Optional<Stall> oneStall = service.getStallById(id);
        return oneStall.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Add stall response entity.
     *
     * @param stallDTO the stallDTO
     * @return the response entity
     */
    @PostMapping("/zoo/stall")
    public ResponseEntity<Stall> addStall(@RequestBody @Valid StallDTO stallDTO) {
        Optional<Stall> optionalStall = service.addStall(stallDTO);
        return optionalStall.map(value
                -> ResponseEntity.created(URI.create("/zoo/stall/"
                + optionalStall.get().getId())).body(value)).orElseGet(()
                -> ResponseEntity.badRequest().build());
    }

    /**
     * Delete stall response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/zoo/stall/{id}")
    public ResponseEntity<Long> deleteStall(@PathVariable Long id) {
        return service.deleteStall(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Put stall response entity.
     *
     * @param stallDTO the stallDTO
     * @return the response entity
     */
    @PutMapping("/zoo/stall")
    public ResponseEntity<Stall> putStall(@RequestBody @Valid StallDTO stallDTO) {
        if (stallDTO.getId() != null && service.isStallAlreadyExists(stallDTO.getId())) {
            Optional<Stall> optionalStall = service.updateStall(stallDTO);
            return  ResponseEntity.ok(optionalStall.get());
        }
        Optional<Stall> optionalStall = service.updateStall(stallDTO);
        return optionalStall.map(value
                -> ResponseEntity.created(URI.create("/zoo/stall/" + value.getId())).body(value)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


}
