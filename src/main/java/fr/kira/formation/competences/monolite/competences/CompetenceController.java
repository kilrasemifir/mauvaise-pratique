package fr.kira.formation.competences.monolite.competences;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competences")
@CrossOrigin
public class CompetenceController {

    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @GetMapping("")
    public Iterable<Competence> getCompetences() {
        return competenceService.getCompetences();
    }

    @PostMapping("")
    public Competence createCompetence(@RequestBody Competence competence) {
        return competenceService.save(competence);
    }

    @PutMapping("")
    public Competence updateCompetence(@RequestBody Competence competence) {
        return competenceService.update(competence);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable String id) {
        competenceService.deleteCompetence(id);
    }

    @GetMapping("/{id}")
    public Competence getCompetence(@PathVariable String id) {
        return competenceService.findById(id);
    }

    @PutMapping("/{id}/prerequis/niveau")
    public void addPrerequis(@PathVariable String id, @RequestParam String competence, @RequestParam int niveau) {
        competenceService.ajouterPrerequis(id, competence, niveau);
    }
}
