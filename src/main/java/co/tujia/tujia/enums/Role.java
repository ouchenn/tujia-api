package co.tujia.tujia.enums;

public enum Role {
    ADMIN("Admin"),
    MOD("Moderator"),
    USER("User");

    private String role;

    /**
     *
     * @param role
     */
    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
