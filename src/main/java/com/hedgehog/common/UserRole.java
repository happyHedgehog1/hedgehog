package com.hedgehog.common;

public enum UserRole {
    SUPER_ADMIN("SUPER_ADMIN"), ADMIN("ADMIN"), MEMBER("MEMBER"), GUEST("GUEST");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
