package by.grechishnikov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 30)
    private String login;
    @Column(nullable = false, length = 30)
    private String password;
    @OneToMany(mappedBy = "voter")
    private List<Vote> votesList = new ArrayList<>();
    @OneToMany(mappedBy = "creator")
    private List<Voting> createdVotingsList = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
