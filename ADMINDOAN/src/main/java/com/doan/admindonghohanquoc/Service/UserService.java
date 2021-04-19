package com.doan.admindonghohanquoc.Service;


import com.doan.admindonghohanquoc.Common.MessageConstant;
import com.doan.admindonghohanquoc.Common.Validate;
import com.doan.admindonghohanquoc.Converter.UserConverter;
import com.doan.admindonghohanquoc.Model.Entity.UserEntity;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.OutPut.UserOutput;
import com.doan.admindonghohanquoc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    public List<UserOutput> getListUser() {
        List<UserOutput> userOutputList= new LinkedList<>();
        try {
            Page<UserEntity> page= userRepository.findAll(
                    PageRequest.of(0,5)
            );
            for (UserEntity userEntity:page) {
                userOutputList.add(userConverter.toUserEntity(userEntity));
            }

        } catch (Exception e) {
        }
        return  userOutputList;
    }
    @Transactional(rollbackOn = Exception.class)
    public String createUserByAdmin(Model model, UserInput userInput) {
        String result = "Themtaikhoan";
        String error = null;
        UserEntity user = null;
        userInput.setUsername(userInput.getEmail());
        try {
            // step 1: validate
            if (Validate.checkRegister(userInput)) {
                // step 2: check email exists

                if (ObjectUtils.isEmpty(userRepository.findByUserName(userInput.getEmail()))) {
                    // convert from register input to user entity
                    user = userConverter.toUserInput(userInput);
                    user.setStatus(1); // con hoat dong

//					user.setPassword(BCrypt.hashpw(userInput.getPassword(), BCrypt.gensalt(12)));

                    // step 3: save
                    userRepository.save(user);

                    // step 4: redirect page login
                    result = "redirect:/taikhoan";
                } else {
                    error = MessageConstant.CREATE_ERROR;
                }
            } else {
                error = MessageConstant.CREATE_ERROR;
            }
        } catch (Exception e) {
            error = MessageConstant.CREATE_ERROR;
        }
        model.addAttribute("error", error);
        return result;
    }
}
