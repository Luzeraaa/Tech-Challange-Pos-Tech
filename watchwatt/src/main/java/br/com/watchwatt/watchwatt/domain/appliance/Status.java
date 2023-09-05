package br.com.watchwatt.watchwatt.domain.appliance;

public enum Status {
    ON("ON"),
    OFF("OFF");

    private final String status;

    Status(String status) {
        this.status= status;
    }

    public String getStatus() {
        return status;
    }
}
