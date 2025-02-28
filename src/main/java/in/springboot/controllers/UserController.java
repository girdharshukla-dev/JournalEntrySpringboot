package in.springboot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.springboot.entity.User;
import in.springboot.service.UserService;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.addUser(user);
    }

    
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception{
        User foundUser = userService.getUserByUserName(user.getUsername());
        if(foundUser!=null){
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            userService.saveUser(foundUser);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   
    
}
