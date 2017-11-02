package by.grechishnikov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vote")
@Data
@NoArgsConstructor
public class Vote implements Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "FK_Voter", nullable = false)
    private User voter;

    public Vote(User voter) {
        date = new Date();
        this.voter = voter;
    }
}
