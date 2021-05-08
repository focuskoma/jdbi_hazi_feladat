package ex10;

import java.time.Year;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LegoSet {

    private String number;
    private Year year;
    private int pieces;

}
