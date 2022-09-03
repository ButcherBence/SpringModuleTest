package app.repositories;

import app.models.Officer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OfficerRepo extends CrudRepository<Officer,Long> {

    Optional<Officer> findByUsername(String username);
}
