package app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for the ships.
 *
 * ATTENTION!
 * Any changes of this class may make it impossible to run the program,
 * since it is in active use.
 * Better not to touch it.
 *
 * (See app.helpers.ShipData for more details.)
 */
@Entity
public class SpaceShip {

    @Id
    @GeneratedValue
    private long registrationCode;

    @Enumerated(EnumType.STRING)
    private SpaceShipClass spaceShipClass;

    @Column(unique = true)
    private String name;

    private boolean isActive;

    @OneToMany(mappedBy = "dutyShip")
    private List<Crew> crews;

    public SpaceShip() {}

    public SpaceShip(SpaceShipClass spaceShipClass, String name, boolean isActive) {
        this.spaceShipClass = spaceShipClass;
        this.name = name;
        this.isActive = isActive;
    }

    public SpaceShip(long registrationCode, SpaceShipClass spaceShipClass, String name, boolean isActive,
                     List<Crew> crews) {
        this(spaceShipClass, name, isActive);
        this.registrationCode = registrationCode;
        this.crews = crews;
    }

    public boolean canFight() {
        if (!isActive) {
            return false;
        }

        long captainAndAdmiralCount = countCaptainsAndAdmirals();
        long crewCount = crews.size() - captainAndAdmiralCount;

        return captainAndAdmiralCount > 1 || (captainAndAdmiralCount > 0 && crewCount > 0);
    }

    private long countCaptainsAndAdmirals() {
        return getCrews().stream()
                .filter(
                        crew -> crew.getCrewRank() == Rank.CAPTAIN || crew.getCrewRank() == Rank.ADMIRAL
                ).count();
    }

    public long getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(long registrationCode) {
        this.registrationCode = registrationCode;
    }

    public SpaceShipClass getSpaceShipClass() {
        return spaceShipClass;
    }

    public void setSpaceShipClass(SpaceShipClass spaceShipClass) {
        this.spaceShipClass = spaceShipClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public List<Crew> getCrews() {
        if (crews == null) {
            crews = new ArrayList<>();
        }

        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public String toString() {
        return  name;
    }
}
