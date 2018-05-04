package be.dto;

public class AutentificareDTO {

    private String email;
    private String password;

    public AutentificareDTO() {
    }

    public AutentificareDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder{
        private String nestedEmail;
        private String nestedPassword;

        public Builder email(String email) {
            this.nestedEmail = email;
            return this;
        }
        public Builder password(String password) {
            this.nestedPassword = password;
            return this;
        }

        public AutentificareDTO create() {
            return new AutentificareDTO(nestedEmail, nestedPassword);
        }

    }
}
