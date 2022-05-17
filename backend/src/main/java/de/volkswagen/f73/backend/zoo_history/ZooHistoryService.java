package de.volkswagen.f73.backend.zoo_history;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * The type Zoo history service.
 */
@Service
@AllArgsConstructor
public class ZooHistoryService {

    private ZooHistoryRepository repository;

    /**
     * Gets full history.
     *
     * @return the full history
     */
    public List<ZooHistory> getFullHistory() {
        return repository.findAll();
    }

    /**
     * Add zoo history to db.
     *
     * @param zooHistory the zoo history
     * @return the list
     */
    public Optional<ZooHistory> addZooHistory(ZooHistory zooHistory) {
        return Optional.of(repository.save(zooHistory));
    }

    /**
     * Delete all history boolean.
     *
     * @return the boolean
     */
    public boolean deleteAllHistory() {
        if (repository.findAll().size() == 0) {
            return false;
        }
        repository.deleteAll();
        return true;
    }
}
