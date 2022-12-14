package fr.kira.formation.competences.monolite.validations;

import fr.kira.formation.competences.monolite.competences.Competence;
import fr.kira.formation.competences.monolite.competences.CompetenceService;
import fr.kira.formation.competences.monolite.personnes.NiveauCompetence;
import fr.kira.formation.competences.monolite.personnes.Personne;
import fr.kira.formation.competences.monolite.personnes.PersonneRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DemandeService {

    private final ValidationRepository validationRepository;
    private final PersonneRepository pr;
    private final CompetenceService competenceService;

    public DemandeService(ValidationRepository validationRepository, PersonneRepository pr, CompetenceService competenceRepository) {
        this.validationRepository = validationRepository;
        this.pr = pr;
        this.competenceService = competenceRepository;
    }

    public DemandeValidation save(DemandeValidation demandeValidation) {
        return validationRepository.insert(demandeValidation);
    }

    public DemandeValidation update(DemandeValidation demandeValidation) {
        return validationRepository.save(demandeValidation);
    }

    public void deleteDemande(String id) {
        validationRepository.deleteById(id);
    }

    public DemandeValidation findById(String id) {
        return validationRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404), "Demande not found"));
    }

    public DemandeValidation validate(String pi, String id){
        var pv = pr.findById(pi).orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404), "Personne not found"));
        var d = findById(id);
        var pvc = pv.getCompetences();
        if (d.getStatut() == "VALIDEE") {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Demande already validated");
        }
        NiveauCompetence rc = null;
        for (var c : pvc) {
            if (c.getCompetence().getId().equals(d.getCompetence().getId())) {
                rc = c;
            }
        }
        if (rc == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Vous ne pouvez pas valider une compétence que vous ne possedez pas");
        } else if (pv.getRole()!="manager"){
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Vous ne pouvez pas valider une compétence que vous ne possedez pas");
        }

        if (d.getNiveauDemander() > rc.getNiveau()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Vous ne pouvez pas valider une compétence que vous ne possedez pas");
        }

        d.setStatut("VALIDEE");
        d.setValideur(pv);
        boolean cond = false;
        for (NiveauCompetence nv: pv.getCompetences()) {
            if (nv.getCompetence().getId().equals(d.getCompetence().getId())){
                nv.setNiveau(d.getNiveauDemander());
                cond = true;
            }
        }
        if (cond == false){
            pv.getCompetences().add(new NiveauCompetence(d.getCompetence(), d.getNiveauDemander()));
        }
        pr.save(pv);
        return update(d);
    }

    public List<DemandeValidation> findAll() {
        return validationRepository.findAll();
    }
}
