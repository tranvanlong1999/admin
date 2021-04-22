package com.doan.admindonghohanquoc.Controller;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Model.Input.UserUpdateInput;
import com.doan.admindonghohanquoc.Model.OutPut.UserOutput;
import com.doan.admindonghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/taikhoan")
public class QuanLyTaiKhoanController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getListUser(Model model) {
        model.addAttribute("listtaikhoan", userService.getListUser());
        model.addAttribute("error", null);
        userService.getListUser();
        return "Quanlytaikhoan";
    }
    @GetMapping("/pagethemtaikhoan")
    public String pageRegister(Model model) {
        model.addAttribute("userInput",new UserInput());
        model.addAttribute("error", null);
        return "Themtaikhoan";
    }
    @PostMapping("/themtaikhoan")
    public String createUserByAdmin(Model model, @ModelAttribute("userInput") UserInput input) {
        return userService.createUserByAdmin(model,input);
    }
    @GetMapping("/xoataikhoan")
    public String deleteUserById(@RequestParam("userid") Integer id,Model model)
    {
        return userService.deleteUserById(id,model);
    }
    @GetMapping("/pagechinhsuataikhoan/{userid}")
    public String getInforUserById(@PathVariable("userid") Integer id, Model model)
    {
       UserOutput userOutput= userService.getInforUserById(id);
       model.addAttribute("userOutPut", userOutput);
       return "Chinhsuataikhoan";
    }
    @PostMapping("/chinhsuataikhoan")
    public String updateUserByAdmin(@ModelAttribute("userOutPut") UserOutput userOutput)
    {
        return userService.updateUserByAdmin(userOutput);
    }

}
