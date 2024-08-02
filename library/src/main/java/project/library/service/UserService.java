package project.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.library.data.repositories.UserRepository;

import project.library.data.entities.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

}
