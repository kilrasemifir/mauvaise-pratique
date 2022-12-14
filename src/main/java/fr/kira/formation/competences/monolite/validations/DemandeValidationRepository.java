package fr.kira.formation.competences.monolite.validations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DemandeValidationRepository extends MongoRepository<DemandeValidation, String> {
}
