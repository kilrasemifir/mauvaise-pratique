package fr.kira.formation.competences.monolite.personnes;

import fr.kira.formation.competences.monolite.changement.Changement;
import fr.kira.formation.competences.monolite.changement.ChangementService;
import fr.kira.formation.competences.monolite.competences.Competence;
import fr.kira.formation.competences.monolite.competences.CompetenceService;
import fr.kira.formation.competences.monolite.competences.Prerequis;
import fr.kira.formation.competences.monolite.competences.PrerequisLvl;
import fr.kira.formation.competences.monolite.validations.DemandeValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonneService {

        private final PersonneRepository personneRepository;
        private final CompetenceService competenceService;
        private final DemandeValidationRepository validationRepository;

        private final ChangementService changementService;

        public PersonneService(PersonneRepository personneRepository, CompetenceService competenceService, DemandeValidationRepository validationRepository, ChangementService changementService) {
            this.personneRepository = personneRepository;
            this.competenceService = competenceService;
            this.validationRepository = validationRepository;
            this.changementService = changementService;
        }

        public Personne save(Personne personne) {
            return personneRepository.save(personne);
        }

        public Personne findById(String id) {
            var re = personneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Personne not found"));
            var dvs = validationRepository.findAll();
            for (var dv: dvs){
                if (dv.getDemandeurId().equals(id)){
                    re.getVosDemandes().add(dv);
                }
            }
            for (var dv: dvs){
                if (re.getRole().equals("manager")) {
                    re.getVosDemandes().add(dv);
                } else {
                    if (dv.getDemandeurId().equals(id)) {

                    } else {
                        for (var c : re.getCompetences()) {
                            if (c.getCompetence().getId().equals(dv.getCompetenceId())) {
                                if (c.getNiveau() >= dv.getNiveauDemander()) {
                                    re.getVosDemandes().add(dv);
                                }
                            }
                        }
                    }
                }
            }
            return re;
        }

        public void deleteById(String id) {
            personneRepository.deleteById(id);
        }

        public Iterable<Personne> findAll() {
            return personneRepository.findAll();
        }

        public void updateLevel(String u, String c, int l) {
            var p = findById(u);
            var c3 = competenceService.findById(c);
            var ch = new Changement();
            ch.setPersonneId(u);
            ch.setCompetenceId(c);
            ch.setNouveauNiveau(l);
            ch.setAncienNiveau(0);
            p.getCompetences().removeIf(c2 -> {
                if (c2.getCompetence().getId().equals(c3.getId())) {
                    ch.setAncienNiveau(c2.getNiveau());
                    return true;
                }
                return c2.getCompetence().getId().equals(c);
            });
            p.getCompetences().add(new NiveauCompetence(c3, l));
            save(p);
            changementService.enregistrerChangement(ch);
        }

        public List<Competence> gca(String i) {
            var p = findById(i);
            var cs = competenceService.getCompetences();
            List<Competence> result = new ArrayList<>();
            for (Competence c : cs) {
                for (Prerequis pr: c.getPrerequis()) {
                    if (pr instanceof PrerequisLvl){
                        var prl = (PrerequisLvl) pr;
                        boolean f = false;
                        for (NiveauCompetence c2: p.getCompetences()){
                            if (c2.getCompetence().getId().equals(prl.getCompetence().getId())){
                                f = true;
                            }
                        }
                        if (f==true){
                            NiveauCompetence c3 = null;
                            for (NiveauCompetence c2: p.getCompetences()){
                                if (c2.getCompetence().getId().equals(prl.getCompetence().getId())){
                                    c3 = c2;
                                }
                            }
                            if (c3.getNiveau()>=prl.getNiveau()){
                                result.add(c);
                            }
                        }
                    }
                }
            }
            return result;
        }


}
