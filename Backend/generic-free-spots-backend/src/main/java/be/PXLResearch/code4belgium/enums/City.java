package be.PXLResearch.code4belgium.enums;

import java.util.Arrays;

public enum City {
    HASSELT,
    GENK,
    DIEPENBEEK,
    ZOLDER,
    ANTWERPEN,
    BRUSSEL,
    GENT;


    public static City fromString(String value) {
        return Arrays.stream(City.values())
                .filter(c -> c.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid city: " + value));
    }
}
