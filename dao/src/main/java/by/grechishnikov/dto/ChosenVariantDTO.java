package by.grechishnikov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for transfer chosen variants list
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChosenVariantDTO {
    private int variantId;
    private int count;
}
