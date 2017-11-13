package by.grechishnikov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "variant")
@Data
@NoArgsConstructor
@ToString(exclude = {"voting", "voteList"})
public class Variant implements Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(length = 140)
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_Voting")
    private Voting voting;
    @JsonIgnore
    @OneToMany(mappedBy = "variant")
    private List<Vote> voteList = new ArrayList<>();

    public Variant(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
