package fr.kira.formation.competences.monolite.validations;

import lombok.Data;

@Data
public class DemandeValidation {
    private String id;
    private String competenceId;
    private int niveauDemander;
    private String demandeurId;
    private String valideurId;
    private String statut = "EN_ATTENTE";
}
