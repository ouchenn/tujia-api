package co.tujia.tujia.enums;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String status;

    /**
     *
     * @param status
     */
    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
