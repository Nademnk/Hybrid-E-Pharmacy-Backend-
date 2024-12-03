package services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import entity.User;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;

@Autowired
private BCryptPasswordEncoder passwordEncoder;

@Transactional
public User registerUser (User user) {
	 System.out.println("Registering user: " + user);
	    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	        throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
	    }
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user.setRoles(Set.of("ROLE_USER"));
	    User savedUser = userRepository.save(user);
	    System.out.println("User saved: " + savedUser);
	    return savedUser;
}
 

public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
}
}



