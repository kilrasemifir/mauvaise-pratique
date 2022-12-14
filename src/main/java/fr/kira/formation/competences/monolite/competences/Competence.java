package fr.kira.formation.competences.monolite.competences;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Competence {
    @Id
    private String id;
    @NonNull
    private String nom;
    @NonNull
    private String description;

    private List<Prerequis> prerequis = new ArrayList<>();

}
