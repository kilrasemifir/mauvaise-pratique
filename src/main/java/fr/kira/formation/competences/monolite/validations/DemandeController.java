package fr.kira.formation.competences.monolite.validations;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/validations")
@RestController
@CrossOrigin
public class DemandeController {

    private final DemandeService validationService;

    public DemandeController(DemandeService validationService) {
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
    public DemandeValidation save(@RequestBody DemandeValidation demandeValidation, @RequestParam String demandeurId) {
        return validationService.save(demandeValidation, demandeurId);
    }

    @PutMapping("")
    public DemandeValidation update(@RequestBody DemandeValidation demandeValidation) {
        return validationService.update(demandeValidation);
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
