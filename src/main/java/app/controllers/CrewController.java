package app.controllers;

import app.forms.CrewMemberSearchForm;
import app.models.Crew;
import app.models.Rank;
import app.models.SpaceShip;
import app.models.SpaceShipClass;
import app.services.CrewService;
import app.services.SpaceShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CrewController {

    private CrewService crewService;

    private SpaceShipService spaceShipService;

    public CrewController(CrewService crewService, SpaceShipService spaceShipService) {
        this.crewService = crewService;
        this.spaceShipService = spaceShipService;
    }

    @GetMapping("/crewmembers")
    public String allSpaceShip(Model model) {
        List<Crew> crewMembers = crewService.findAllCrewMember();
        model.addAttribute("crewmembers", crewMembers);

        return "crewmembers";
    }

    @GetMapping("/search_crewmember")
    public String searchQuestions(Model model) {
        List<Crew> result = crewService.findAllCrewMember();
        model.addAttribute("form", new CrewMemberSearchForm());
        model.addAttribute("result", result);

        return "search_crewmember";
    }

    @PostMapping("/search_crewmember")
    public String displaySearchResults(CrewMemberSearchForm form, Model model) {
        List<Crew> result = crewService.getByForm(form);
        model.addAttribute("form", form);
        model.addAttribute("result", result);

        return "search_crewmember";
    }

    @GetMapping(value = {"/crewmember"})
    public String saveNewSpaceShip(Model model) {
        List<SpaceShip> spaceShips = spaceShipService.findAllSpaceShip();
        model.addAttribute("newMember", new Crew());
        model.addAttribute("crewRanks", Rank.values());
        model.addAttribute("ship", spaceShips);

        return "crewmember_new";
    }

    @PostMapping("/crewmember")
    public String addSpaceShip(Crew crew) {
        if (crew.getDutyShip() != null) {
            crewService.saveCrewMember(crew);
            return "redirect:/crewmembers";
        }

        return "unsuccessfull";


    }

}
