package fr.kira.formation.competences.monolite.equipes;

import org.springframework.stereotype.Service;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe findById(String id) {
        return equipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipe not found"));
    }

    public void deleteById(String id) {
        equipeRepository.deleteById(id);
    }

    public Iterable<Equipe> findAll() {
        return equipeRepository.findAll();
    }
}
