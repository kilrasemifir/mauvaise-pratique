package fr.kira.formation.competences.monolite.personnes;

import fr.kira.formation.competences.monolite.competences.Competence;
import fr.kira.formation.competences.monolite.competences.CompetenceService;
import fr.kira.formation.competences.monolite.competences.Prerequis;
import fr.kira.formation.competences.monolite.competences.PrerequisLvl;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonneService {

        private final PersonneRepository personneRepository;
        private final CompetenceService competenceService;

        public PersonneService(PersonneRepository personneRepository, CompetenceService competenceService) {
            this.personneRepository = personneRepository;
            this.competenceService = competenceService;
        }

        public Personne save(Personne personne) {
            return personneRepository.save(personne);
        }

        public Personne findById(String id) {
            return personneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Personne not found"));
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
            p.getCompetences().removeIf(c2 -> c2.getCompetence().getId().equals(c));
            p.getCompetences().add(new NiveauCompetence(c3, l));
            save(p);
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
