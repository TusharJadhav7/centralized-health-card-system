package com.avirat.chc.serviceTest;

import com.avirat.chc.entity.UserEntity;
import com.avirat.chc.repo.UserRepo;
import com.avirat.chc.requestdto.UserRequestDTO;
import com.avirat.chc.responnsedto.UserResponseDTO;
import com.avirat.chc.service.UserService;
import com.avirat.chc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private UserRepo userRepo;
//
//    @InjectMocks
//    private UserServiceImpl userServiceImpl ;
//
//    @Test
//    public void signUpTest(){
//
//        UserRequestDTO requestDTO = getUserRequestDTO();
//        UserEntity userEntity = getUserEntity();
//
//        Mockito.when(modelMapper.map(Mockito.any(UserRequestDTO.class),Mockito.any())).thenReturn(userEntity);
//        Mockito.when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
//
//              String Sigup = userServiceImpl.signUp(requestDTO);
//        Assertions.assertEquals("User is registered successfully" , Sigup);
//
//
//    }
//
//    public UserRequestDTO getUserRequestDTO(){
//        return UserRequestDTO.builder().userName("Tushar123")
//                .firstName("Tushar")
//                .lastName("Jadhav")
//                .password("112233")
//                .healthCardNo(456).build();
//
//    }
//
//    public UserEntity getUserEntity(){
//        return UserEntity.builder().userName("Tushar123")
//                .firstName("Tushar")
//                .lastName("Jadhav")
//                .password("112233")
//                .healthCardNo(456).build();
//
//    }
//
//    public UserResponseDTO getUserResponseDTO(){
//       return UserResponseDTO.builder().userName("Tushar123")
//               .firstName("Tushar")
//               .lastName("Jadhav")
//               .password("112233")
//               .healthCardNo(456).build();
//
//    }

}
