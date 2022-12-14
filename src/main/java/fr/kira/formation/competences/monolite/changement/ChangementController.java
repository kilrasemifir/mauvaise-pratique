package fr.kira.formation.competences.monolite.changement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/changements")
public class ChangementController {

    @Autowired
    private ChangementService changementService;

    @GetMapping("")
    public Iterable<Changement> findAll() {
        return changementService.getChangements();
    }

    @GetMapping("{id}")
    public Changement findById(String id) {
        return changementService.getChangement(id);
    }
}
