package de.volkswagen.f73.backend.zoo_account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Zoo account repository.
 */
public interface ZooAccountRepository extends JpaRepository<ZooAccount,Long> {
}
