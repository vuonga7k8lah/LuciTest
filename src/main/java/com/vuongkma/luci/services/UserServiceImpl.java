package com.vuongkma.luci.services;

import com.vuongkma.luci.entities.UserEntity;
import com.vuongkma.luci.exceptions.NotFoundException;
import com.vuongkma.luci.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String bcryptEncryptor(String plainText) {
        return this.passwordEncoder.encode(plainText);
    }

    public Boolean isPasswordMatched(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean existsById(Long id) {
        return this.userRepository.existsById(id);
    }

    public UserEntity insert(UserEntity user) {
        user.setPassword(this.bcryptEncryptor(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Transactional
    public UserEntity update(Long id, UserEntity userInfo) {
        var user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("This user does not exists"));

        if (userInfo.getEmail() != null && !userInfo.getEmail().isBlank()) {
            user.setEmail(userInfo.getEmail());
        }

        if (userInfo.getName() != null && !userInfo.getName().isBlank()) {
            user.setName(userInfo.getName());
        }
        System.out.println(userInfo.getUsername());
        if (userInfo.getUsername() != null && !userInfo.getUsername().isBlank()) {
            user.setUsername(userInfo.getUsername());
        }

        if (userInfo.getPhone() != null && !userInfo.getPhone().isBlank()) {
            user.setPhone(userInfo.getPhone());
        }

        if (userInfo.getPassword() != null && !userInfo.getPassword().isBlank()) {
            user.setPassword(this.bcryptEncryptor(userInfo.getPassword()));
        }

        this.userRepository.save(user);
        return user;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        this.userRepository.deleteById(id);

        return true;
    }

    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public UserEntity findUser(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Oops! This user does not exists"));
    }

    public void updateNewPassword(UserEntity userEntity, String password) {
        userEntity.setPassword(this.bcryptEncryptor(password));
    }
}
