package com.doan.admindonghohanquoc.Controller;
import com.doan.admindonghohanquoc.Model.Input.UserInput;
import com.doan.admindonghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/taikhoan")
public class QuanLyTaiKhoanController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getListUser(Model model) {
        model.addAttribute("listtaikhoan", userService.getListUser());
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

}
