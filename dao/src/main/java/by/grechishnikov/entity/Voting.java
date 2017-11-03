package by.grechishnikov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "voting")
@Data
@NoArgsConstructor
@ToString(exclude = "variantsList")
public class Voting implements Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private boolean active;
    @Column
    private Date openDate;
    @Column
    private Date closingDate;
    @ManyToOne
    @JoinColumn(name = "FK_Creator", nullable = false)
    private User creator;
    @OneToMany(mappedBy = "voting")
    private List<Variant> variantsList = new ArrayList<>();

    public Voting(String name, String description, Date closingDate, User creator) {
        this.name = name;
        this.description = description;
        this.closingDate = closingDate;
        this.creator = creator;
        this.active = true;
        this.openDate = new Date();
    }
}
