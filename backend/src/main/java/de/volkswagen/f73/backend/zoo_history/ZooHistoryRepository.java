package de.volkswagen.f73.backend.zoo_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Zoo history repository.
 */
@Repository
public interface ZooHistoryRepository extends JpaRepository<ZooHistory, Long> {


}
