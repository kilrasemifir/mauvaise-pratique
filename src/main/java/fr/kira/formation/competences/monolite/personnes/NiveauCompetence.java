package fr.kira.formation.competences.monolite.personnes;

import fr.kira.formation.competences.monolite.competences.Competence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NiveauCompetence {
    @DBRef
    private Competence competence;
    private int niveau;
}
