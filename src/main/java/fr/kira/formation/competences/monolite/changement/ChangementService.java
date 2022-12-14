package fr.kira.formation.competences.monolite.changement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChangementService {

    @Autowired
    private ChangementRepository changementRepository;

    public Changement enregistrerChangement(Changement changement) {
        return changementRepository.save(changement);
    }

    public List<Changement> getChangements() {
        return changementRepository.findAll();
    }

    public Changement miseAJour(Changement changement) {
        return changementRepository.save(changement);
    }

    public void supprimerChangement(String id) {
        changementRepository.deleteById(id);
    }

    public Changement getChangement(String id) {
        return changementRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
