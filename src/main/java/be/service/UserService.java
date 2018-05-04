package be.service;


import be.dto.AutentificareDTO;
import be.dto.PasswordDTO;
import be.dto.UserDTO;
import be.dto.converters.UserDTOConverter;
import be.errorhandler.EntityValidationException;
import be.errorhandler.InvalidTipUserException;
import be.errorhandler.ResourceNotFoundException;
import be.errorhandler.UnauthorizedException;
import be.mailservice.Mail;
import be.mailservice.MailService;
import be.model.User;
import be.repository.UserRepository;
import be.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("UserService")
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return UserDTOConverter.convertUsersList(userRepository.findAll());
    }

    public UserDTO findById(int idUser) {
        User user = userRepository.findOne(idUser);
        if (user == null) {
            throw new ResourceNotFoundException(User.class.getSimpleName());
        }
        return UserDTOConverter.convertToUserDTO(user);
    }

    public UserDTO loginUser(AutentificareDTO user) {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb != null) {
            if (passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())) {
                return UserDTOConverter.convertToUserDTO(userFromDb);
            }
        }
        throw new UnauthorizedException(User.class.getSimpleName());
    }

    public UserDTO registerUser(User user) {
        User userFromDb = userRepository.findOne(user.getId_user());
        if (userFromDb == null) {
            List<String> validationErrors = validateUser(user);
            if (!validationErrors.isEmpty()) {
                throw new EntityValidationException(User.class.getSimpleName(), validationErrors);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User usr = userRepository.save(user);
            return UserDTOConverter.convertToUserDTO(usr);
        } else {
            return updateUser(user);
        }
    }

    public UserDTO updateUser(User user) {
        List<String> validationErrors = validateUserForUpdate(user);
        if (!validationErrors.isEmpty()) {
            throw new EntityValidationException(User.class.getSimpleName(), validationErrors);
        }
        User userFromDb = userRepository.findOne(user.getId_user());
        if (userFromDb != null) {
            if (user.getEmail() != null) {
                userFromDb.setEmail(user.getEmail());
            }
            if (user.getName() != null) {
                userFromDb.setName(user.getName());
            }
            if (user.getPhone() != null) {
                userFromDb.setPhone(user.getPhone());
            }
            if (user.getAddress() != null) {
                userFromDb.setAddress(user.getAddress());
            }
            return UserDTOConverter.convertToUserDTO(userRepository.save(userFromDb));
        }
        throw new ResourceNotFoundException(User.class.getSimpleName());
    }

    public UserDTO changePassword(PasswordDTO password) {
        User user = userRepository.findOne(password.getId_user());
        if (user == null) {
            throw new ResourceNotFoundException(User.class.getSimpleName());
        }
        List<String> validationErrors = new ArrayList<>();
//        if (!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
//            validationErrors.add("Old password is incorrect!");
//        }
        if (password.getNewPassword() != null && password.getNewPassword().length() < 2) {
            validationErrors.add("Password should have at least 4 characters.");
        }
        if (!validationErrors.isEmpty()) {
            throw new EntityValidationException(User.class.getSimpleName(), validationErrors);
        }
        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        return UserDTOConverter.convertToUserDTO(userRepository.save(user));
    }

    private List<String> validateUser(User user) {
        List<String> validationErrors = new ArrayList<String>();
        if (user.getEmail() == null || !Utils.validateEmail(user.getEmail())) {
            validationErrors.add("Email does not have the correct format.");
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            validationErrors.add("Email already exists in database.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 4) {
            validationErrors.add("Password should have at least 4 characters.");
        }
        return validationErrors;
    }

    private List<String> validateUserForUpdate(User user) {
        List<String> validationErrors = new ArrayList<String>();
        if (user.getEmail() != null && !Utils.validateEmail(user.getEmail())) {
            validationErrors.add("Email does not have the correct format.");
        } else {
            User userWithSameEmail = userRepository.findByEmail(user.getEmail());
            if (userWithSameEmail != null && user.getId_user() != userWithSameEmail.getId_user()) {
                validationErrors.add("Email already exists in database.");
            }
        }
        return validationErrors;
    }

    public void setUserRepository(UserRepository usrRepository) {
        this.userRepository = usrRepository;
    }
}
