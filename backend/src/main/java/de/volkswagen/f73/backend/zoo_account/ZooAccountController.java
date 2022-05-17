package de.volkswagen.f73.backend.zoo_account;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * The type Zoo account controller.
 */
@RestController
@AllArgsConstructor
public class ZooAccountController {

    private ZooAccountService service;

    /**
     * Gets all entries.
     *
     * @return the all entries
     */
    @GetMapping("/zoo/account")
    public ResponseEntity<List<ZooAccount>> getAllEntries() {
        List<ZooAccount> zooAccountList = service.getAllEntries();
        return zooAccountList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zooAccountList);
    }

    /**
     * Add entry response entity.
     *
     * @param zooAccount the zoo account
     * @return the response entity
     */
    @PostMapping("/zoo/account")
    public ResponseEntity<ZooAccount> addEntry(@RequestBody @Valid ZooAccount zooAccount) {
        Optional<ZooAccount> optionalZooAccount = service.addZooAccount(zooAccount);
        return optionalZooAccount.map(value
                -> ResponseEntity.created(URI.create("/zoo/account/"
                + optionalZooAccount.get().getId())).body(value)).orElseGet(()
                -> ResponseEntity.badRequest().build());
    }

    /**
     * Delete by account response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("zoo/account/{id}")
    public ResponseEntity<?> deleteByAccount(@PathVariable Long id) {
        return service.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
