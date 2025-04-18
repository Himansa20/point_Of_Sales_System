
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
 @Autowired
 private UserRepo userRepo;
 
  @Autowired
  private ModelMapper modelMapper;
  
  public List<UserDTO> getAlluser(){
  List<User>UserList=userRepo.findAll();
  return modelMapper.map(UserList, new TypeToken<List<UserDTO>>(){
  }.getType());
  }
}
