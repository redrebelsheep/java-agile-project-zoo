package de.volkswagen.f73.backend.zoo_account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Zoo account service.
 */
@Service
@AllArgsConstructor
public class ZooAccountService {

    private ZooAccountRepository repository;

    /**
     * Gets all entries.
     *
     * @return the all entries
     */
    public List<ZooAccount> getAllEntries() {
        return repository.findAll();
    }

    /**
     * Add zoo account optional.
     *
     * @param zooAccount the zoo account
     * @return the optional
     */
    public Optional<ZooAccount> addZooAccount(ZooAccount zooAccount) {
        return Optional.of(repository.save(zooAccount));
    }

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteById(Long id) {
        if (checkIfZooAccountExists(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Check if zoo account exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean checkIfZooAccountExists(Long id) {
        return repository.existsById(id);
    }

}
