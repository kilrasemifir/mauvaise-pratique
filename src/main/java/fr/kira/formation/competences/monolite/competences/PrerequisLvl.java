package fr.kira.formation.competences.monolite.competences;

import fr.kira.formation.competences.monolite.personnes.Personne;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
public class PrerequisLvl extends Prerequis {

    private int niveau;
    private Competence competence;

    public PrerequisLvl(int niveau, Competence competence) {
        this.niveau = niveau;
        this.competence = competence;
    }


    @Override
    public String getType() {
        return "LVL";
    }

    @Override
    public boolean isSatisfied(Personne personne) {
        return personne.getCompetences().stream()
                .anyMatch(nc->nc.getCompetence().equals(competence) && nc.getNiveau() >= niveau);
    }
}
