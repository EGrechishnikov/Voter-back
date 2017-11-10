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
    @ManyToOne
    @JoinColumn(name = "FK_Variant", nullable = false)
    private Variant variant;

    public Vote(User voter, Variant variant) {
        date = new Date();
        this.voter = voter;
        this.variant = variant;
    }
}
