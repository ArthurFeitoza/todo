package org.example;

public enum Status {
    ACTIVE("A"), INACTIVE("I"), COMPLETED("C"), STARTED("S");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status getEnum(String value) {
        for (Status s : values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException();
    }
}
