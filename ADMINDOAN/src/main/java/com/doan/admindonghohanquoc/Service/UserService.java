package com.doan.admindonghohanquoc.Service;


import com.doan.admindonghohanquoc.Common.Constant;
import com.doan.admindonghohanquoc.Common.MessageConstant;
import com.doan.admindonghohanquoc.Common.PageConstant;
import com.doan.admindonghohanquoc.Common.Validate;
import com.doan.admindonghohanquoc.Constants.Constants;
import com.doan.admindonghohanquoc.Converter.UserConverter;
import com.doan.admindonghohanquoc.Model.Entity.UserEntity;
import com.doan.admindonghohanquoc.Model.Input.LoginInput;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.Input.UserUpdateInput;
import com.doan.admindonghohanquoc.Model.OutPut.UserOutput;
import com.doan.admindonghohanquoc.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        List<UserOutput> userOutputList = new LinkedList<>();
        try {
            Page<UserEntity> page = userRepository.findAll(
                    PageRequest.of(0, 5)
            );
            for (UserEntity userEntity : page) {
                userOutputList.add(userConverter.toUserEntity(userEntity));
            }

        } catch (Exception e) {
        }
        return userOutputList;
    }

    @Transactional(rollbackOn = Exception.class)
    public String createUserByAdmin(Model model, UserInput userInput) {
        String result = "Themtaikhoan";
        System.out.println(userInput);
        String error = null;
        UserEntity user = null;
        userInput.setUsername(userInput.getEmail());
        try {
            // step 1: validate
            if (Validate.checkRegister(userInput)) {
                // step 2: check email exists

                if (ObjectUtils.isEmpty(userRepository.findByEmail(userInput.getEmail()))) {
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

    @Transactional(rollbackOn = Exception.class)
    public String deleteUserById(Integer id, Model model) {
        String error = null;
        String result = "Quanlytaikhoan";
        try {
            // get user by ID
            UserEntity userEntity = userRepository.findById(id).get();
            if (ObjectUtils.isEmpty(userEntity)) {
                error = MessageConstant.DELETE_ERROR;
            } else {
                userRepository.delete(userEntity);
            }
        } catch (Exception e) {
            error = MessageConstant.DELETE_ERROR;
        }
        model.addAttribute("error", error);
        return "redirect:/taikhoan";
    }

    @Transactional(rollbackOn = Exception.class)
    public String updateUserByAdmin(UserOutput userOutput) {
        String error = null;
        String result = "Chinhsuataikhoan";
        UserEntity userEntity;
        try {
            UserInput userInput = userConverter.convertToUserOutput(userOutput);
            if (Validate.checkRegister(userInput)) {
                // case email ???? c?? trong h??? th???ng
                /*if (!ObjectUtils.isEmpty(userRepository.findByEmailAndIDNot(userOutput.getEmail(), userOutput.getId()))) {
                    error = MessageConstant.CHECK_EMAIL;
                    return result;
                }*/
                // get userEntity ra
                userEntity= userRepository.findById(userOutput.getId()).get();
                System.out.println(userEntity);
                userEntity.setFullName(userInput.getFullname());
                userEntity.setEmail(userInput.getEmail());
                userEntity.setPassWord(userInput.getPassword());
                userEntity.setPhone(userInput.getPhone());
                userEntity.setAddress(userInput.getAddress());
                // step 3: save
                userRepository.save(userEntity);
                // step 4: redirect page quan ly tai kho???n
                result = "redirect:/taikhoan";
            }
        } catch (Exception e) {
            error = MessageConstant.EDIT_USER;
        }
        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    public UserOutput getInforUserById(Integer id) {
        String error = null;
        UserOutput output = null;
        UserEntity user;
        try {
            // get user in db
            user = userRepository.findById(id).orElse(null);
            // case user is null or empty
            if (ObjectUtils.isEmpty(user)) {
                throw new Exception("User not exist");
            }
            // convert from user to user info output
            output = userConverter.convertToUserInfoOutput(user);

        } catch (Exception e) {

        }
        return output;
    }


    // home
    public String pageLogout(Model model, HttpSession session, HttpServletResponse response) {
        session.removeAttribute("user");
        model.addAttribute("error", null);
        // remove a cookie email
        Cookie cookieEmail = new Cookie("email", null);
        cookieEmail.setMaxAge(0); // expires in 7 days

        // remove a cookie password
        Cookie cookiePassword = new Cookie("password", null);
        cookiePassword.setMaxAge(0); // expires in 7 days

        // remove a cookie remember
        Cookie cookieRemember = new Cookie("remember", null);
        cookieRemember.setMaxAge(0); // expires in 7 days

        // add cookie to response
        response.addCookie(cookieEmail);
        response.addCookie(cookiePassword);
        response.addCookie(cookieRemember);

        return "redirect:/dang-nhap";
    }
    // longin
    public String login(Model model, HttpSession session, HttpServletResponse response, LoginInput userForm) {
        String result = PageConstant.PAGE_LOGIN;
        String message = null;
        try {
            if (Validate.checkLogin(userForm)) {
                UserEntity user = userRepository.findByEmailAndPassWord(userForm.getEmail(),userForm.getPassword());

                if (ObjectUtils.isEmpty(user)) {
                    message = MessageConstant.LOGIN_ERROR;
                } else {
                    if (userForm.isRemember()) {
                        // create a cookie email
                        Cookie cookieEmail = new Cookie("email", user.getEmail());
                        cookieEmail.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                        // create a cookie password
                        Cookie cookiePassword = new Cookie("password", user.getPassWord());
                        cookiePassword.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                        // remove a cookie remember
                        Cookie cookieRemember = new Cookie("remember", "" + userForm.isRemember());
                        cookieRemember.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                        // add cookie to response
                        response.addCookie(cookieEmail);
                        response.addCookie(cookiePassword);
                        response.addCookie(cookieRemember);
                    } else {
                        // remove a cookie email
                        Cookie cookieEmail = new Cookie("email", null);
                        cookieEmail.setMaxAge(0); // expires in 7 days

                        // remove a cookie password
                        Cookie cookiePassword = new Cookie("password", null);
                        cookiePassword.setMaxAge(0); // expires in 7 days

                        // remove a cookie remember
                        Cookie cookieRemember = new Cookie("remember", null);
                        cookieRemember.setMaxAge(0); // expires in 7 days

                        // add cookie to response
                        response.addCookie(cookieEmail);
                        response.addCookie(cookiePassword);
                        response.addCookie(cookieRemember);
                    }
                    // save session
                    session.setAttribute("user", user);
                    session.setAttribute("email",user.getEmail());
                    result= "redirect:/home";
                }
            } else {
                message = MessageConstant.LOGIN_ERROR;
            }
        } catch (Exception e) {
            message = MessageConstant.LOGIN_ERROR;
        }

        model.addAttribute("error", message);
        return result;
    }
    //regist
    public String register(Model model, UserInput userInput) {
        String result = PageConstant.PAGE_REGISTER;
        String error = null;
        UserEntity user = null;
        try {
            // step 1: validate
            if (Validate.checkRegister(userInput)) {
                // step 2: check email exists
                if (ObjectUtils.isEmpty(userRepository.findByEmail(userInput.getEmail()))) {
                    // convert from register input to user entity
                    System.out.println("aa");
                    userInput.setRole(Constants.ROLE_MEMBER);
                    user = userConverter.toUserInput(userInput);
                    user.setStatus(Constant.STATUS_ENABLE);

//					user.setPassword(BCrypt.hashpw(userInput.getPassword(), BCrypt.gensalt(12)));

                    // step 3: save
                    userRepository.save(user);

                    // step 4: redirect page login
                    result = "redirect:/dang-nhap";
                } else {
                    error = MessageConstant.RIGISTER_ERROR;
                }
            } else {
                error = MessageConstant.RIGISTER_ERROR;
            }
        } catch (Exception e) {
            error = MessageConstant.RIGISTER_ERROR;
        }
        model.addAttribute("error", error);
        return result;
    }
}
