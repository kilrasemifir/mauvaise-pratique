package fr.kira.formation.competences.monolite.equipes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipes")
@CrossOrigin
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("")
    public Iterable<Equipe> findAll() {
        return equipeService.findAll();
    }

    @GetMapping("/{id}")
    public Equipe findById(@PathVariable String id) {
        return equipeService.findById(id);
    }

    @PostMapping("")
    public Equipe save(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @PutMapping("")
    public Equipe update(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        equipeService.deleteById(id);
    }

}
