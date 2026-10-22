package com.avirat.chc.service.impl;

import com.avirat.chc.entity.UserEntity;
import com.avirat.chc.repo.UserRepo;
import com.avirat.chc.requestdto.UserRequestDTO;
import com.avirat.chc.responnsedto.UserResponseDTO;
import com.avirat.chc.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class    UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override

    public String signUp(UserRequestDTO userRequestDTO) {

        log.info("sign Up method is call");
        //DTO  -->>  Entity
        UserEntity userEntity = modelMapper.map(userRequestDTO, UserEntity.class);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        // Save in UserEntity.
        log.info("Data is convert into user Entity {} {} ", userEntity.getUserId());
        UserEntity save = userRepo.save(userEntity);
        String msg = null;
        if(save!=null) {
            msg = "User is registered successfully";
            log.info("User is registered successfully");
        }
        else{
            msg="register is not successfully";
        }
        return msg;
    }

    @Override
    public UserResponseDTO loginId(String userName,String password) {

        // user pasun user cha data Db madun baher kadala => Db madala password ani user ni dilela password check karaycha
        UserEntity userEntity = userRepo.findByUserName(userName).get();

        if(userEntity.getPassword().equals(password)){

            //userEntity ==>> UserRepoDTO
            return modelMapper.map(userEntity,UserResponseDTO.class);
        }
        return null;
    }


    @Override
    public String updateData(Integer userId, UserRequestDTO userRequestDTO) {

        //DB mathun data baher kadala.
       UserEntity userEntity =  userRepo.findById(userId).get();
        // Alela Data Db mathe update kela.

        userEntity.setUserName(userRequestDTO.getUserName());
        userEntity.setPassword(userRequestDTO.getPassword());

       UserEntity  save = userRepo.save(userEntity);

       String msg=null;
       if(save!=null)
           return msg="data is updated";
       else
           return msg="data is not updated";


    }

    @Override
    public UserResponseDTO getUserData(Integer userId) {
       UserEntity userEntity = userRepo.findById(userId).get();


        return modelMapper.map(userEntity,UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getByUserName(String userName) {
        UserEntity u = userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(u.getUserId());
        dto.setUserName(u.getUsername());
        dto.setFirstName(u.getFirstName());
        dto.setLastName(u.getLastName());
        dto.setEmail(u.getEmail());
        dto.setRole(u.getRole());
        dto.setHealthCardNo(u.getHealthCardNo());
        dto.setContact(u.getContact());
        dto.setDob(u.getDob());
        dto.setDoctorRegNo(u.getDoctorRegNo());
        dto.setChemistRegNo(u.getChemistRegNo());
        dto.setAddress(u.getAddress());
        dto.setGender(u.getGender());
        // baaki fields (contact, dob, gender, address, doctorRegNo, chemistRegNo) isi tarah
        return dto;   // password set nahi kiya, to bahar nahi jayega
    }
}
