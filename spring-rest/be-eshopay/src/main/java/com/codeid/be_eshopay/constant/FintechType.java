package com.codeid.be_eshopay.constant;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FintechType {
    E_WALLET("E-Wallet"),
    P_GATEWAY("P-GateWay"),
    BANK("BANK");

    @JsonValue
    private final String value;

    @JsonCreator
    public static FintechType fromString(String value) {
        for (FintechType type : FintechType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new BadRequestException("Unexpected value: " + value, null);
    }
}
