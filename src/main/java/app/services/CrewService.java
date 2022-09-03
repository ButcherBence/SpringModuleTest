package app.services;

import app.forms.CrewMemberSearchForm;
import app.models.Crew;
import app.models.SpaceShip;
import app.repositories.CrewRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrewService {

    private CrewRepo crewRepo;

    public CrewService(CrewRepo crewRepo) {
        this.crewRepo = crewRepo;
    }

    public List<Crew> findAllCrewMember(){

        return (List<Crew>) crewRepo.findAll();
    }

    public List<Crew> getByForm(CrewMemberSearchForm form) {
        List<Crew> result = new ArrayList<>();

        for (Crew question : findAllCrewMember()) {
            if (form.getName() != null && !question.getCrewName().toLowerCase().contains(form.getName().toLowerCase())) {
                continue;
            }
            result.add(question);
        }

        return result;
    }

    public void saveCrewMember(Crew crew) {
        crewRepo.save(crew);
    }

}
