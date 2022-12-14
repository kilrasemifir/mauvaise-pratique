package fr.kira.formation.competences.monolite.validations;

import fr.kira.formation.competences.monolite.changement.Changement;
import fr.kira.formation.competences.monolite.changement.ChangementService;
import fr.kira.formation.competences.monolite.competences.CompetenceService;
import fr.kira.formation.competences.monolite.personnes.NiveauCompetence;
import fr.kira.formation.competences.monolite.personnes.PersonneRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DemandeService {

    private final DemandeValidationRepository validationRepository;
    private final ChangementService changementService;

    public DemandeValidation save(DemandeValidation demandeValidation, String demandeurId) {
        if (pr.existsById(demandeurId)) {
            demandeValidation.setDemandeurId(demandeurId);
            return validationRepository.save(demandeValidation);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Personne not found");
        }
    }

    public DemandeValidation update(DemandeValidation demandeValidation) {
        return validationRepository.save(demandeValidation);
    }

    public void deleteDemande(String id) {
        validationRepository.deleteById(id);
    }

    public DemandeValidation findById(String id) {
        var r = validationRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404), "Demande not found"));
        return r;
    }



    public DemandeService(DemandeValidationRepository validationRepository, ChangementService changementService, PersonneRepository pr, CompetenceService competenceRepository) {
        this.validationRepository = validationRepository;
        this.changementService = changementService;
        this.pr = pr;
        this.competenceService = competenceRepository;
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
            if (c.getCompetence().getId().equals(d.getCompetenceId())) {
                rc = c;
            }
        }

        var ch = new Changement();
        ch.setCompetenceId(d.getCompetenceId());
        ch.setPersonneId(d.getDemandeurId());
        ch.setAncienNiveau(0);

        if (rc == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Vous ne pouvez pas valider une compétence que vous ne possedez pas");
        } else if (pv.getRole() != "manager"){
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Vous ne pouvez pas valider une compétence que vous ne possedez pas");
        }

        if (d.getNiveauDemander() > rc.getNiveau()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Vous ne pouvez pas valider une compétence que vous ne possedez pas");
        }

        d.setStatut("VALIDEE");
        d.setValideurId(pv.getId());
        boolean cond = false;
        for (NiveauCompetence nv: pv.getCompetences()) {
            if (nv.getCompetence().getId().equals(d.getCompetenceId())) {
                nv.setNiveau(d.getNiveauDemander());
                cond = true;
            }
        }
        if (cond == false){
            var cc = competenceService.findById(d.getCompetenceId());
            pv.getCompetences().removeIf(c -> {
                if (c.getCompetence().getId().equals(d.getCompetenceId())) {
                    ch.setAncienNiveau(c.getNiveau());
                    return true;
                }
                return c.getCompetence().getId().equals(d.getCompetenceId());
            });
            ch.setNouveauNiveau(d.getNiveauDemander());
            pv.getCompetences().add(new NiveauCompetence(cc, d.getNiveauDemander()));
        }
        changementService.enregistrerChangement(ch);
        pr.save(pv);
        return update(d);
    }
    private final CompetenceService competenceService;

    public List<DemandeValidation> findAll() {
        return validationRepository.findAll();
    }


    private final PersonneRepository pr;
}
