package by.grechishnikov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChosenVariantDTO {
    private int variantId;
    private int count;
}
