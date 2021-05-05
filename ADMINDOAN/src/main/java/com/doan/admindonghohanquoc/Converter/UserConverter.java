package com.doan.admindonghohanquoc.Converter;

import com.doan.admindonghohanquoc.Model.Entity.UserEntity;
import com.doan.admindonghohanquoc.Model.Input.LoginInput;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.Input.UserUpdateInput;
import com.doan.admindonghohanquoc.Model.OutPut.UserOutput;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
public class UserConverter {
    public UserOutput toUserEntity(UserEntity userEntity)
    {
        UserOutput userOutput= new UserOutput();
        if(!ObjectUtils.isEmpty(userEntity))
        {
            userOutput.setId(userEntity.getID());
            userOutput.setFullname(userEntity.getFullName());
            userOutput.setPhone(userEntity.getPhone());
            userOutput.setAddress(userEntity.getAddress());
            userOutput.setEmail(userEntity.getEmail());
            userOutput.setPassword(userEntity.getPassWord());
            if(userEntity.getStatus()==1)
                userOutput.setStatus("Hoạt động");
        }
        return userOutput;
    }
    public UserEntity  toUserInput(UserInput userInput)
    {
        UserEntity userEntity = new UserEntity();
        if(!ObjectUtils.isEmpty(userInput))
        {
            //Trong đó BCrypt.gensalt xác định số vòng, số vòng dao động từ 4-30, số vòng càng lớn thì thời gian thực hiện băm càng lâu.
           /* userEntity.setPassWord(BCrypt.hashpw(userInput.getPassword(),BCrypt.gensalt(12)));*/
            userEntity.setPassWord(userInput.getPassword());
            userEntity.setFullName(userInput.getFullname());
            userEntity.setPhone(userInput.getPhone());
            userEntity.setAddress(userInput.getAddress());
            userEntity.setStatus(1);
            userEntity.setRole(2); // la nhân viên
            userEntity.setEmail(userInput.getEmail());
            userEntity.setCreateBy(userInput.getFullname());
            userEntity.setJoinedAt(new Date());
        }
        return userEntity;
    }

    public UserEntity  toLoginInput(LoginInput userInput)
    {
        UserEntity userEntity = new UserEntity();
        if(!ObjectUtils.isEmpty(userInput))
        {
            /*userEntity.setPassWord(BCrypt.hashpw(userInput.getPassword(),BCrypt.gensalt(12)));*/
            userEntity.setPassWord(userInput.getPassword());
        }
        return userEntity;
    }
    /*public UserEntity toLoginInput(LoginInput loginInput)
    {
        UserEntity userEntity= new UserEntity();
        if(!ObjectUtils.isEmpty(loginInput))
        {
            userEntity.setUserName(loginInput.getUsername());
            userEntity.setPassWord(loginInput.getPassword());
        }

        return userEntity;
    }*/
    public UserEntity toUserUpdateInput(UserUpdateInput userUpdateInput)
    {
        UserEntity userEntity = new UserEntity();
        if(!ObjectUtils.isEmpty(userUpdateInput))
        {
            userEntity.setID(userUpdateInput.getId());
            if(!userUpdateInput.getPassword().isEmpty())
                userEntity.setPassWord(BCrypt.hashpw(userUpdateInput.getNewPassword(),BCrypt.gensalt(12)));
            userEntity.setFullName(userUpdateInput.getFullname());
            userEntity.setPhone(userUpdateInput.getPhone());
            userEntity.setAddress(userUpdateInput.getAddress());
            userEntity.setForgetCode(userUpdateInput.getForgetcode());
            userEntity.setStatus(userUpdateInput.getStatus());
            userEntity.setRole(userUpdateInput.getRole());
            userEntity.setEmail(userUpdateInput.getEmail());
            userEntity.setForgetCodeDate(userUpdateInput.getForgetcodedate());
            userEntity.setJoinedAt(userUpdateInput.getJoinedat());
            userEntity.setCreateBy(userUpdateInput.getCreatedby());
            userEntity.setUpdatedAt(userUpdateInput.getUpdatedat());
            userEntity.setUdatedBy(userUpdateInput.getUpdatedby());
        }
        return userEntity;
    }
    public  UserOutput convertToUserInfoOutput(UserEntity user) {
        UserOutput output = new UserOutput();
        if(!ObjectUtils.isEmpty(user)) {
            output.setId(user.getID());
            output.setFullname(user.getFullName());
            output.setEmail(user.getEmail());
            output.setPassword(user.getPassWord());
            output.setPhone(user.getPhone());
            output.setAddress(user.getAddress());
            if(user.getStatus()==1)
                output.setStatus("Hoạt động");
        }
        return output;
    }
    public UserInput convertToUserOutput(UserOutput userOutput)
    {
        UserInput userInput= new UserInput();
        if(!ObjectUtils.isEmpty(userOutput))
        {
            userInput.setId(userOutput.getId());
            userInput.setFullname(userOutput.getFullname());
            userInput.setEmail(userOutput.getEmail());
            userInput.setPassword(userOutput.getPassword());
            userInput.setPhone(userOutput.getPhone());
            userInput.setAddress(userOutput.getAddress());
        }
        return userInput;
    }
}
