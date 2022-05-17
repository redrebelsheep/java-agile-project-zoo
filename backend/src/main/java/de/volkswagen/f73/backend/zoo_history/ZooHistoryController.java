package de.volkswagen.f73.backend.zoo_history;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * The type Zoo history controller.
 */
@RestController
@AllArgsConstructor
public class ZooHistoryController {

    private ZooHistoryService service;

    /**
     * Gets full history.
     *
     * @return the full history
     */
    @GetMapping("/zoo/history")
    public ResponseEntity<List<ZooHistory>> getFullHistory() {
        List<ZooHistory> zooHistory = service.getFullHistory();
        return zooHistory.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zooHistory);
    }



    /**
     * Add entry response entity.
     *
     * @param zooHistory the zoo account
     * @return the response entity
     */
    @PostMapping("/zoo/history")
    public ResponseEntity<ZooHistory> addZooHistory(@RequestBody @Valid ZooHistory zooHistory){
        Optional<ZooHistory> optionalZooAccount = service.addZooHistory(zooHistory);
        return optionalZooAccount.map(value
                -> ResponseEntity.created(URI.create("/zoo/account/"
                + optionalZooAccount.get().getId())).body(value)).orElseGet(()
                -> ResponseEntity.badRequest().build());
    }


    /**
     * Delete all history response entity.
     *
     * @return the response entity
     */
    @DeleteMapping("zoo/history")
    public ResponseEntity<?> deleteAllHistory() {
        return service.deleteAllHistory() ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
