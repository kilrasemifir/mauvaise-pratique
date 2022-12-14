package fr.kira.formation.competences.monolite.changement;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChangementRepository extends MongoRepository<Changement, String> {
}
