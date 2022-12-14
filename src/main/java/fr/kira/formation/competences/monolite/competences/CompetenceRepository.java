package fr.kira.formation.competences.monolite.competences;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetenceRepository extends MongoRepository<Competence, String> {
}
