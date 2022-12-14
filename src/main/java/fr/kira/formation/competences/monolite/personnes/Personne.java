package fr.kira.formation.competences.monolite.personnes;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private List<NiveauCompetence> competences = List.of();
    private String role = "membre";
}
