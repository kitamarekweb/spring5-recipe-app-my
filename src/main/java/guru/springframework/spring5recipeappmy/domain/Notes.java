package guru.springframework.spring5recipeappmy.domain;

import lombok.*;

import javax.persistence.*;


// tak wyglada kod od lomboka, to generuje pod spodem adnotacji @Data
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Notes() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Notes;
    }

}
