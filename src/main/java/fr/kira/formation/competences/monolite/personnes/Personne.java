package fr.kira.formation.competences.monolite.personnes;

import fr.kira.formation.competences.monolite.validations.DemandeValidation;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Personne {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private List<NiveauCompetence> competences =  new ArrayList<>();
    private String role = "membre";

    @Transient
    private List<DemandeValidation> vosDemandes = new ArrayList<>();
    @Transient
    private List<DemandeValidation> validationPossible = new ArrayList<>();
}
