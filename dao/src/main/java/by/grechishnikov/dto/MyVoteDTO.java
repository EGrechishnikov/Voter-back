package by.grechishnikov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for transfer current users vote list
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyVoteDTO {
    private int votingId;
    private int variantId;
}
