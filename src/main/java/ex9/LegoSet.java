package ex9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LegoSet {

    private Long id;
    private String number;
    private int year;
    private int pieces;

    public LegoSet(String number, int year, int pieces) {
        this(null, number, year, pieces);
    }

}
