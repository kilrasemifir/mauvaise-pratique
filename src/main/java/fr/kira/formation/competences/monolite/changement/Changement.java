package fr.kira.formation.competences.monolite.changement;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Changement {
    @Id
    private String id;
    private String competenceId;
    private int ancienNiveau;
    private int nouveauNiveau;
    private String personneId;
    private LocalDateTime date = LocalDateTime.now();
}
