//<<<<<<< HEAD
//=======

//>>>>>>> fdd7c5254b3082338dc26244341ca468d8964db2
package com.possystem.pos.service;

import com.possystem.pos.dto.UserDTO;
import com.possystem.pos.model.User;
import com.possystem.pos.repo.UserRepo;
import jakarta.transaction.Transactional;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
//<<<<<<< HEAD

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAlluser() {
        List<User> UserList = userRepo.findAll();
        return modelMapper.map(UserList, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    public UserDTO saveUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }
        public UserDTO updateUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }
        public String deleteUSer(Integer userId){
//        userRepo.delete(modelMapper.map(userDTO, User.class));
          userRepo.deleteById(userId);
        return "User Deleted !";
        }
        
        public UserDTO getUserById(Integer userId){
        User user = userRepo.getUserById(userId);
        return modelMapper.map(user,UserDTO.class);
        }
//=======
 @Autowired
 private UserRepo userRepo;
 
  @Autowired
  private ModelMapper modelMapper;
  
  public List<UserDTO> getAlluser(){
  List<User>UserList=userRepo.findAll();
  return modelMapper.map(UserList, new TypeToken<List<UserDTO>>(){
  }.getType());
  }
//>>>>>>> fdd7c5254b3082338dc26244341ca468d8964db2
}
