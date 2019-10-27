package com.demo.entrepreneur.entity;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum TaxGroup {
    GENERAL(0, false), I(1, false), II(2, false), III(3, true), IV(4, false), V(5, true), UNDEFINED(-1, false);

    private TaxGroup(Integer groupNumber, boolean supported) {
        this.groupNumber = groupNumber;
        this.supported = supported;
        Mapping.taxGroupsByGroupNumber.put(groupNumber, this);
    }

    private final Integer groupNumber;
    private final boolean supported;

    public static TaxGroup valueOfGroupNumber(Integer groupNumber) {
        return Mapping.taxGroupsByGroupNumber.getOrDefault(groupNumber, UNDEFINED);
    }

    private interface Mapping {
        public static final Map<Integer, TaxGroup> taxGroupsByGroupNumber = Maps.newHashMap();
    }
}
