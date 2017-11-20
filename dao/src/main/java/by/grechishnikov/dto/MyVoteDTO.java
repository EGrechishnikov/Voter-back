package by.grechishnikov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyVoteDTO {
    private int votingId;
    private int variantId;
}
