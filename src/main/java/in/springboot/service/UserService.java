package in.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.springboot.entity.User;
import in.springboot.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    
    public void addUser(User user) {
       userRepository.save(user);
    }
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) throws Exception{
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new Exception("Entry not found")));
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }


    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


    public void updateUserById(Long id, String username, String password) throws Exception{
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("Entry not found"));
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
