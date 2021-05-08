package ex8;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import lombok.Data;

@Data
public class LegoSet {

    private String number;
    private int year;
    private int pieces;
    private Set<String> tags = new HashSet<>();

    public LegoSet() {
    }

    public LegoSet(String number, int year, int pieces, String... tags) {
        this.number = number;
        this.year = year;
        this.pieces = pieces;
        Collections.addAll(this.tags, tags);
    }

    public LegoSet addTag(String tag) {
        tags.add(tag);
        return this;
    }

}
