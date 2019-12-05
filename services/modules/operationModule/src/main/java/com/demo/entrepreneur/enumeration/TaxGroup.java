package com.demo.entrepreneur.enumeration;

import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TaxGroup {
    GENERAL(0, false),
    I(1, false),
    II(2, false),
    III(3, true),
    IV(4, false),
    V(5, true);

    private final Integer groupNumber;
    private final boolean supported;

    public static TaxGroup valueOfGroupNumber(Integer groupNumber) {
        return Stream.of(values())// @formatter:off
                .filter(value -> value.getGroupNumber().equals(groupNumber))
                .findAny()
                .orElseThrow(
                () -> new RuntimeException(String.format("TaxGroup value for groupNumber %s not found", groupNumber)));// @formatter:on
    }
}
