package app.services;

import app.models.SpaceShip;
import app.repositories.SpaceShipRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceShipService {

    private SpaceShipRepo spaceShipRepo;

    public SpaceShipService(SpaceShipRepo spaceShipRepo) {
        this.spaceShipRepo = spaceShipRepo;
    }

    public List<SpaceShip> findAllSpaceShip() {
        return (List<SpaceShip>) spaceShipRepo.findAll();
    }


    public List<SpaceShip> findActiveSpaceShip() {
        return (List<SpaceShip>) spaceShipRepo.findSpaceShipByisActiveTrue();
    }

    public SpaceShip findSpaceShip(long registrationCode) {
        List<SpaceShip> spaceShips = findAllSpaceShip();
        SpaceShip spaceShip = new SpaceShip();
        for (SpaceShip x : spaceShips) {
            if (x.getRegistrationCode() == registrationCode) {
                spaceShip = x;
            }
        }
        return spaceShip;
    }

    public void saveSpaceShip(SpaceShip spaceShip) {
        spaceShipRepo.save(spaceShip);
    }


}
