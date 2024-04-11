package com.nas.authen.service.enums;

public enum RoleType {
    CLIENT("Client"),
    DRIVER("Driver"),
    RESTAURANT("Restaurant"),
    SHOP("Shop");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
