package be.PXLResearch.code4belgium.enums;

import java.util.Arrays;

public enum DataType {
    INTEGER,
    STRING,
    LONG,
    FLOAT,
    BOOLEAN;

    public static DataType fromString(String value) {
        return Arrays.stream(DataType.values())
                .filter(d -> d.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid datatype: " + value));
    }
}
