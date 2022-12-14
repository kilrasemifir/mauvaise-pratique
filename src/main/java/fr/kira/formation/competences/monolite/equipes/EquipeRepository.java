package fr.kira.formation.competences.monolite.equipes;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipeRepository extends MongoRepository<Equipe, String> {
}
