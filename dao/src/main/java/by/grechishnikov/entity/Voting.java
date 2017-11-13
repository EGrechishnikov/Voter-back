package by.grechishnikov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Entity
@Table(name = "voting")
@Data
@NoArgsConstructor
@ToString(exclude = {"variants", "image"})
public class Voting implements Bean {
    private static int defaultAccessPeriod =
            Integer.parseInt(ResourceBundle.getBundle("config").getString("default.voting.access.period"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String imageLink;
    @Column
    private Date openDate;
    @Column
    private Date closingDate;
    @ManyToOne
    @JoinColumn(name = "FK_Creator", nullable = false)
    private User creator;
    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Variant> variants = new ArrayList<>();

    private transient byte[] image;

    public Voting(String name, String description, int accessPeriod, User creator) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.openDate = new Date();
        this.closingDate = new Date(System.currentTimeMillis() + (accessPeriod * 1000));
    }

    public Voting(String name, String description, User creator) {
        this(name, description, defaultAccessPeriod, creator);
    }

    public void setClosingDate() {
        this.closingDate = new Date(System.currentTimeMillis() + (defaultAccessPeriod * 1000));
    }
}
