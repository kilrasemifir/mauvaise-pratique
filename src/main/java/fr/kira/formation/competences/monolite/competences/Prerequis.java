package fr.kira.formation.competences.monolite.competences;


import fr.kira.formation.competences.monolite.personnes.Personne;
import lombok.Data;

@Data
public class Prerequis {
    private String type;

    public boolean isSatisfied(Personne personne) {
        return false;
    }
}
