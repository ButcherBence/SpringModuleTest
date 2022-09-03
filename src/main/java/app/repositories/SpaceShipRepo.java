package app.repositories;

import app.models.SpaceShip;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpaceShipRepo extends CrudRepository<SpaceShip, Long> {

    List<SpaceShip> findSpaceShipByisActiveTrue();
}
