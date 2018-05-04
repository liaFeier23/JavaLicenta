package be.dto.converters;

import be.dto.UserDTO;
import be.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOConverter {

    public static List<UserDTO> convertUsersList(List<User> users) {
        List<UserDTO> list = new ArrayList<>();
        for (User user : users) {
            list.add(convertToUserDTO(user));
        }
        return list;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId_user())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone())
                .setAddress(user.getAddress())
                .setFavourite(user.getFavourite())
                .setRating(user.getRating())
                .setRole(user.getRole());
        return userDTO;
    }
}
