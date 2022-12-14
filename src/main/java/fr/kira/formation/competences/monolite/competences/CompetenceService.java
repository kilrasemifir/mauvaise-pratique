package fr.kira.formation.competences.monolite.competences;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompetenceService {

    private final CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    public Competence save(Competence competence) {
        return competenceRepository.insert(competence);
    }

    public Competence update(Competence competence) {
        return competenceRepository.save(competence);
    }

    public void deleteCompetence(String id) {
        competenceRepository.deleteById(id);
    }

    public Competence findById(String id) {
        return competenceRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404), "Competence not found"));
    }

    public List<Competence> getCompetences() {
        return competenceRepository.findAll();
    }

    public Competence ajouterPrerequis(String id, String competence, int niveau) {
        var c = findById(id);
        c.getPrerequis().add(new PrerequisLvl(niveau,findById(competence)));
        return update(c);
    }


}
