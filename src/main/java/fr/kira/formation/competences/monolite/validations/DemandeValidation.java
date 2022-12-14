package fr.kira.formation.competences.monolite.validations;

import fr.kira.formation.competences.monolite.competences.Competence;
import fr.kira.formation.competences.monolite.personnes.Personne;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class DemandeValidation {
    private String id;
    @DBRef
    private Competence competence;
    private int niveauDemander;
    @DBRef
    private Personne demendeur;
    @DBRef
    private Personne valideur;
    private String statut = "EN_ATTENTE";
}
