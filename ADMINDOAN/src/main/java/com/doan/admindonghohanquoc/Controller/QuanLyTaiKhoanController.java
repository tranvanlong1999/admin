package com.doan.admindonghohanquoc.Controller;

import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.OutPut.UserOutput;
import com.doan.admindonghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuanLyTaiKhoanController {
    @Autowired
    private UserService userService;
    UserOutput userOutput;
    @GetMapping("/taikhoan")
    public String getListUser(Model model) {
        model.addAttribute("listtaikhoan", userService.getListUser());
        model.addAttribute("error", null);
        return "Quanlytaikhoan";
    }

    @GetMapping("/pagethemtaikhoan")
    public String pageRegister(Model model) {
        model.addAttribute("userInput", new UserInput());
        model.addAttribute("error", null);
        return "Themtaikhoan";
    }

    @PostMapping("/themtaikhoan")
    public String createUserByAdmin(Model model, @ModelAttribute("userInput") UserInput input) {
        return userService.createUserByAdmin(model, input);
    }

    @GetMapping("/xoataikhoan")
    public String deleteUserById(@RequestParam("userid") Integer id, Model model) {
        return userService.deleteUserById(id, model);
    }

    @GetMapping("/pagechinhsuataikhoan/{userid}")
    public String getInforUserById(@PathVariable("userid") Integer id, Model model) {
        userOutput = userService.getInforUserById(id);
        model.addAttribute("userOutPut", userOutput);
        return "Chinhsuataikhoan";
    }

    @PostMapping("/chinhsuataikhoan")
    public String updateUserByAdmin(@ModelAttribute("userOutPut") UserOutput userinfo) {
        userinfo.setId(userOutput.getId());
        return userService.updateUserByAdmin(userinfo);
    }
}
