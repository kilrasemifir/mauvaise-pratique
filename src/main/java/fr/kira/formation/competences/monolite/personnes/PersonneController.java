package fr.kira.formation.competences.monolite.personnes;

import fr.kira.formation.competences.monolite.competences.Competence;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnes")
@CrossOrigin
public class PersonneController {
    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping("")
    public Iterable<Personne> findAll() {
        return personneService.findAll();
    }

    @GetMapping("/{id}")
    public Personne findById(@PathVariable String id) {
        return personneService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        personneService.deleteById(id);
    }

    @PostMapping("")
    public Personne save(@RequestBody Personne personne) {
        return personneService.save(personne);
    }

    @PutMapping("")
    public Personne update(@RequestBody Personne personne) {
        return personneService.save(personne);
    }

    @PutMapping("/{id}/competences/{competenceId}")
    public void uLvl(@PathVariable String id, @PathVariable String competenceId, @RequestParam int lvl) {
        personneService.updateLevel(id, competenceId, lvl);
    }

    @GetMapping("/{id}/competences/accessibles")
    public Iterable<Competence> getCompetencesAccessibles(@PathVariable String id) {
        return personneService.gca(id);
    }
}
