package com.example.Crud.Operation.service;

import com.example.Crud.Operation.entity.User;
import com.example.Crud.Operation.exception.UserNotFoundException;
import com.example.Crud.Operation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User addUser(User user)
    {
        return userRepository.save(user);
    }

    // Get ALl User
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    // getBy Id
    public User getUserById(Long id)
    {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(
                ()->new UserNotFoundException("user not found with id " + id));
    }

    // delete By id
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    // find By Email
    public User getUserByEmail(String email)
    {
        return userRepository.getUserByEmail(email);
    }


}
