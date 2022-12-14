package fr.kira.formation.competences.monolite.validations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ValidationRepository extends MongoRepository<DemandeValidation, String> {
}
