package fr.kira.formation.competences.monolite.validations;

import fr.kira.formation.competences.monolite.personnes.Personne;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/validations")
@RestController
@CrossOrigin
public class ValidationController {

    private final DemandeService validationService;

    public ValidationController(DemandeService validationService) {
        this.validationService = validationService;
    }

    @GetMapping("")
    public Iterable<DemandeValidation> findAll() {
        return validationService.findAll();
    }

    @GetMapping("{id}")
    public DemandeValidation findById(String id) {
        return validationService.findById(id);
    }

    @PostMapping("")
    public DemandeValidation save(@RequestBody DemandeValidation demandeValidation) {
        return validationService.save(demandeValidation);
    }

    @PutMapping("")
    public DemandeValidation update(@RequestBody DemandeValidation demandeValidation) {
        return validationService.save(demandeValidation);
    }

    @DeleteMapping("{id}")
    public void deleteById(String id) {
        validationService.deleteDemande(id);
    }

    @PutMapping("{id}/valider")
    public DemandeValidation valider(@PathVariable String id, @RequestParam String personne) {
        return validationService.validate(personne, id);
    }

}
