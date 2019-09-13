package com.demo.entrepreneur.model.enumeration;

import java.util.stream.Stream;

public enum Currency {
    EUR,
    USD,
    BTC,
    UAH;

    public static boolean isValid(String value) {
        return Stream.of(Currency.values()).anyMatch(elem -> elem.name().equals(value));
    }
}
