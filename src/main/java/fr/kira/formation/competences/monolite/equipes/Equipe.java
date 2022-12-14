package fr.kira.formation.competences.monolite.equipes;

import fr.kira.formation.competences.monolite.personnes.Personne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Equipe {

    @Id
    private String id;
    private String nom;
    private String description;
    @DBRef
    private List<Personne> membres;
}
