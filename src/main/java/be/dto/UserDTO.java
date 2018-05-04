package be.dto;

import be.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {

    private int id_user;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer favourite;
    private Float rating;
    private String role;

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getFavourite() {
        return favourite;
    }

    public UserDTO setFavourite(Integer favourite) {
        this.favourite = favourite;
        return this;
    }

    public Float getRating() {
        return rating;
    }

    public UserDTO setRating(Float rating) {
        this.rating = rating;
        return this;
    }

    public int getId() {
        return id_user;
    }

    public UserDTO setId(int id_user) {
        this.id_user = id_user;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDTO setRole(String role) {
        this.role = role;
        return this;
    }

}
