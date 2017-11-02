package by.grechishnikov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "variant")
@Data
@NoArgsConstructor
public class Variant implements Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(length = 140)
    private String description;
    @Column
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "FK_Voting", nullable = false)
    private Voting voting;

    public Variant(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
