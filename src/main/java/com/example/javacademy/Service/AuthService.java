package com.example.javacademy.Service;

import com.example.javacademy.Model.User;
import com.example.javacademy.Repository.InMemoryRepo;
import com.example.javacademy.exceptions.InvalidArgumentsException;
import com.example.javacademy.exceptions.InvalidUserCredentialsException;
import com.example.javacademy.exceptions.PasswordsDoNotMatchException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    private final InMemoryRepo userRepository;

    public AuthService(InMemoryRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        User user=userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
        return user;
    }

    public User register(String username, String password, String repeatPassword, String ime, String prezime) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        if ( !password.equals(repeatPassword) ){
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(username,password,ime,prezime);
        return userRepository.saveOrUpdate(user);
    }
}
