package be.PXLResearch.code4belgium.enums;

import java.util.Arrays;

public enum City {
    HASSELT,
    GENK,
    DIEPENBEEK,
    ZOLDER,
    ANTWERP,
    BRUSSELS,
    GHENT;


    public static City fromString(String value) {
        return Arrays.stream(City.values())
                .filter(c -> c.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid city: " + value));
    }
}
