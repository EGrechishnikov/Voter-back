package by.grechishnikov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@ToString(exclude = {"votesList", "createdVotingList"})
public class User implements Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 30)
    private String login;
    @Column(nullable = false, length = 30)
    private String password;
    @Column
    private String salt;
    @OneToMany(mappedBy = "voter")
    @JsonIgnore
    private List<Vote> votesList = new ArrayList<>();
    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Voting> createdVotingList = new ArrayList<>();

    public User(String login, String password, String salt) {
        this.login = login;
        this.password = password;
        this.salt = salt;
    }
}
