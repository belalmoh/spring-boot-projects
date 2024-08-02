package project.library.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;

    @ColumnDefault("false")
    private boolean borrowed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy;
}
