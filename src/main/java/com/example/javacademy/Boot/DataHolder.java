package com.example.javacademy.Boot;

import com.example.javacademy.Model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<User> users= new ArrayList<>();
@PostConstruct
public void init(){
    users.add(new User("admin","admin123","AdminN","AdminS"));
    users.add(new User("user","user123","UserName","UserSurname"));
    users.add(new User("elonmusk","elonmusk123","Elon","Musk"));
}
}
