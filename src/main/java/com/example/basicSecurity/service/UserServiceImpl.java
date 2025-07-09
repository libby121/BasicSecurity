package com.example.basicSecurity.service;

import com.example.basicSecurity.entity.User;
import com.example.basicSecurity.model.UserDTO;
import com.example.basicSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
communication with the client is through the serialized object (dto).
send away a model  after converting the regular pojo object that was fetched from database.

 */
@Service
public class UserServiceImpl implements UserService{


    private UserRepo userRepo;


    private AuthenticationManager manager ;


    private JWTService Jwtserv;

    private BCryptPasswordEncoder coder=new BCryptPasswordEncoder(15);

    public UserServiceImpl(UserRepo userRepo, AuthenticationManager manager, JWTService jwtserv) {
        this.userRepo = userRepo;
        this.manager = manager;
        Jwtserv = jwtserv;

    }

    @Override
    public UserDTO register(UserDTO u){
        User uu = convertToEntity(u);
        uu.setPassword(coder.encode(uu.getPassword()));
        UserDTO uuu=convertToDTO(uu);
         userRepo.save(uu);
         return uuu;

    }
    @Override
    public String verify(UserDTO u){
        User uu = convertToEntity(u);
        Authentication authentication =
                manager.authenticate
                        (new UsernamePasswordAuthenticationToken
                                (uu.getName(),uu.getPassword()));
       if(authentication.isAuthenticated())
          return Jwtserv.generateToken(uu.getName());
       return "no..";



    }

    @Override
    public void addUsers(List<UserDTO> users) {
       //if Jwtserv.validateToken
        for(UserDTO dt :users){
            User u = convertToEntity(dt);
            userRepo.save(u);
        }
    }

    @Override
    public UserDTO getUser(int userId) throws Exception {
        User u= userRepo.findById(userId);
        UserDTO usertd= convertToDTO(u);
        return usertd;
    }
    /*
    helper methods for converting entity to model (DTO) and vice versa
     */
    private User convertToEntity(UserDTO dt){
        User user = new User();
        user.setId((dt.getId()));
        user.setName(dt.getName());
        user.setPassword(dt.getPassword());
        return user;
    }
    private UserDTO convertToDTO(User user){
        UserDTO userdt = new UserDTO();
        userdt.setId(user.getId());
        userdt.setName(user.getName());
        userdt.setPassword(user.getPassword());
        return userdt;
    }
}
