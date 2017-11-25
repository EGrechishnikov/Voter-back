package by.grechishnikov.dto;

import by.grechishnikov.entity.Voting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for pagination on voting list page
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingsDTO {
    private List<Voting> list;
    private long recordsCount;
}
